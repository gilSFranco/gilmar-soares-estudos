package com.compasso.parking_management_spring_boot.web.controller;

import com.compasso.parking_management_spring_boot.entities.Vehicle;
import com.compasso.parking_management_spring_boot.exception.EntityNotFoundException;
import com.compasso.parking_management_spring_boot.service.VehicleService;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.CreateVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseUpdatePaymentDTO;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseVehicleDTO;
import com.compasso.parking_management_spring_boot.web.dto.mapper.VehicleMapper;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.UpdatePaymentDTO;
import com.compasso.parking_management_spring_boot.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(
        name = "Vehicles",
        description = "Vehicle operations"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Operation(
            summary = "Vehicle registration",
            description = "Resource to create a new vehicle.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Resource created successfully.",
                            headers = @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "URL de acesso ao recurso criado"
                            ),
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
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ResponseVehicleDTO> create(@RequestBody @Valid CreateVehicleDTO vehicleDTO) {
        Vehicle createdVehicle = vehicleService.registerVehicle(vehicleDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdVehicle.getId()).toUri();

        return ResponseEntity.created(uri).body(VehicleMapper.toDTO(createdVehicle));
    }

    @Operation(
            summary = "Vehicle inquiry",
            description = "You can use the license plate filter or return all registered vehicles.",
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
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ResponseVehicleDTO>> getAll(@RequestParam(value = "plate", defaultValue = "") String plate) {
        List<Vehicle> listVehicles = new ArrayList<>();
        if (plate.isEmpty()) {
            listVehicles = vehicleService.findAll();
            if(listVehicles.isEmpty()){
                throw new EntityNotFoundException("No vehicles registered.");
            }
        } else {
            Optional<Vehicle> vehicleOpt = vehicleService.findByPlate(plate);
            if (vehicleOpt.isPresent()){
                listVehicles.add(vehicleOpt.get());
                return ResponseEntity.ok(VehicleMapper.toListDTO(vehicleOpt));
            } else {
                throw new EntityNotFoundException(String.format("Vehicles by plate '%s' not found.", plate));
            }
        }

        return ResponseEntity.ok(VehicleMapper.toListDTO(listVehicles));
    }

    @Operation(
            summary = "Vehicle removal",
            description = "Resource to remove registered vehicles.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Resource successfully removed.",
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
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Changing vehicles",
            description = "Resource to change registered vehicles.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Resource changed successfully.",
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
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PatchMapping
    public ResponseEntity<ResponseUpdatePaymentDTO> update(@RequestBody @Valid UpdatePaymentDTO paymentDTO) {
        return ResponseEntity.ok((vehicleService.updateVehicle(paymentDTO)));
    }
}
