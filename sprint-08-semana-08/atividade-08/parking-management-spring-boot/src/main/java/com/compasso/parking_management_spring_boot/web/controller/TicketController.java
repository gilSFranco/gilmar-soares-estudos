package com.compasso.parking_management_spring_boot.web.controller;

import com.compasso.parking_management_spring_boot.entities.ParkingSpot;
import com.compasso.parking_management_spring_boot.entities.Ticket;
import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
import com.compasso.parking_management_spring_boot.exception.*;
import com.compasso.parking_management_spring_boot.service.ParkingSpotService;
import com.compasso.parking_management_spring_boot.service.TicketService;
import com.compasso.parking_management_spring_boot.service.VehicleService;
import com.compasso.parking_management_spring_boot.web.dto.mapper.TicketMapper;
import com.compasso.parking_management_spring_boot.web.dto.ticket.ResponseTicketDTO;

import com.compasso.parking_management_spring_boot.web.dto.ticket.TicketDTO;
import com.compasso.parking_management_spring_boot.web.dto.ticket.OutDTO;

import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseVehicleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(
        name = "Tickets",
        description = "Ticket operations"
)
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final VehicleService vehicleService;
    private final TicketService ticketService;
    private final ParkingSpotService parkingSpotService;

    @Operation(
            summary = "Consultation of tickets registered in the system.",
            description = "A query with filters or a query without filters can be made, which would return all tickets registered in the system.",
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "plate",
                            description = "License plate of the vehicle already registered in the system."
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Resource located successfully.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseVehicleDTO.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseTicketDTO>> getAll(
            @RequestParam(value = "plate", defaultValue = "") String plate
    ) {
        List<Ticket> listTickets = ticketService.findAll(plate);
        if(listTickets.isEmpty()){
            throw new EntityNotFoundException(String.format("Ticket with vehicle plate '%s' not found", plate));
        }
        return ResponseEntity.ok(TicketMapper.toListDTO(listTickets));
    }


    @Operation(
            summary = "Registers new parking entries.",
            description = "Feature to register new parking entries.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Resource created successfully.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseVehicleDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Resource with invalid fields.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseVehicleDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "No spaces available for the vehicle.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseVehicleDTO.class)
                            )
                    )
            }
    )
    @PostMapping("/enter")
    public ResponseEntity<ResponseTicketDTO> park(@RequestBody @Valid TicketDTO ticketDTO) {
        Boolean existingVacancies = parkingSpotService.verifyExistingVacancies();
        if(!existingVacancies){
            throw new NoVacanciesStartedException("To insert a new vehicle you need to start the vacancies");
        }

        if(ticketDTO.getType().equals("DELIVERYTRUCK")){
            Optional<Vehicle> vehicleToVerify = vehicleService.findByPlate(ticketDTO.getLicensePlate());
            if(vehicleToVerify.isEmpty()){
                throw new EntityNotFoundException(String.format(
                        "The delivery truck by plate '%s' isn't registered.", ticketDTO.getLicensePlate()));
            }
        }


        /*
        The ideal would be to create a transaction on service and do all this verification involving the 3 layers.
        The @Transactional annotation in Spring Boot is used to ensure that a set of database operations are executed
        within a transaction. If any of the operations fails, the entire transaction is rolled back, ensuring data consistency.
         */
        Vehicle vehicle = vehicleService.findByPlate(ticketDTO.getLicensePlate())
                .orElseGet(() -> vehicleService.register(
                        new Vehicle(ticketDTO.getLicensePlate(), TypeVehicle.valueOf(ticketDTO.getType()))));

        if(vehicle.getType() != TypeVehicle.valueOf(ticketDTO.getType())){
            throw new ConflictWithTypesException("Vehicle type incompatible with the license plate registered in our database." +
                    " Check the license plate and vehicle type");
        }

        List<ParkingSpot> parkingSpot = parkingSpotService.findByPlate(vehicle.getLicensePlate());
        if(parkingSpot.isEmpty()){  // check if the vehicle is not parked
            if (parkingSpotService.gateInVerification(ticketDTO.getIn(), vehicle)) {

                vehicle.setRegister(true);

                List<ParkingSpot> parkingSpots = parkingSpotService.spotsWanted(vehicle);  // encontra vagas

                if (parkingSpots.isEmpty() && vehicle.getType() == TypeVehicle.PUBLICSERVICE) {

                    Ticket newTicket = ticketService.register(vehicle, "0", ticketDTO.getIn());  // park public vehicles
                    return ResponseEntity.ok(TicketMapper.toDTO(newTicket));
                }
                else if(parkingSpots.isEmpty()) {
                    throw new NoSuchAvailableVacancies("No such available vacancies");
                }
                else{  // generate the ticket and print it
                    Ticket newTicket = ticketService.register(vehicle, parkingSpotService.convert(parkingSpots), ticketDTO.getIn());
                    return ResponseEntity.ok(TicketMapper.toDTO(newTicket));
                }
            }
        } else{
            throw new VehicleAlreadyParkedException("Vehicle already parked.");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Generic error response
    }

    @Operation(
            summary = "Registers the departure of a vehicle and calculates the amount to be paid.",
            description = "Resource to register the departure of a vehicle and calculate the amount to be paid.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Resource located successfully.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseVehicleDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseVehicleDTO.class)
                            )
                    )
            }
    )
    @PostMapping("/{id}/leave")
    public ResponseEntity<ResponseTicketDTO> leave(@PathVariable Long id, @RequestBody @Valid OutDTO o) {
        Optional<Ticket> optionalTicket = ticketService.findById(id);

        Ticket ticket = null;

        if (optionalTicket.isPresent()) {
            ticket = optionalTicket.get();

            if(ticket.getVehicle().getCategory() == Category.PUBLIC) {
                ticket.setParked(false);
                ticket.setExitDate(LocalDateTime.now());
                ticket.setExitGate(o.getOut());
                ticketService.update(ticket);

                return ResponseEntity.ok(TicketMapper.toDTO(ticket));
            }

            if (parkingSpotService.gateOutVerification(o.getOut(), ticket.getVehicle())) {
                if (ticket.getVehicle().getCategory() == Category.ONETIME) {
                    double charge = ticketService.charge(optionalTicket);
                    ticket.setValue(charge);
                }
                parkingSpotService.markSpotsAsUnoccupied(ticket);
                ticket.setExitDate(LocalDateTime.now());
                ticket.setExitGate(o.getOut());
                ticket.setParked(false);
                ticketService.update(ticket);
                return ResponseEntity.ok(TicketMapper.toDTO(ticket));
            }
        } else {
            throw new EntityNotFoundException(String.format("Ticket by id '%s' not found.", id));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Generic error response
    }
}
