package com.compasso.parking_management_spring_boot.web.controller;

import com.compasso.parking_management_spring_boot.exception.EntityNotFoundException;
import com.compasso.parking_management_spring_boot.exception.NoSuchAvailableVacancies;
import com.compasso.parking_management_spring_boot.web.dto.vehicle.ResponseVehicleDTO;
import com.compasso.parking_management_spring_boot.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.compasso.parking_management_spring_boot.entities.ParkingSpot;
import com.compasso.parking_management_spring_boot.service.ParkingSpotService;
import com.compasso.parking_management_spring_boot.web.dto.spot.SpotDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(
        name = "Spots",
        description = "Spot operations"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/spots")
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @Operation(
            summary = "Check available parking spaces",
            description = "Resource for checking available parking spaces.",
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
    public ResponseEntity<Map<String, Integer>> getSpots() {
        return ResponseEntity.ok().body(formatter());
    }

    @Operation(
            summary = "Change parking capacity",
            description = "Resource to change parking capacity.",
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
                            description = "Requested capacity is invalid.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Parking capacity limit reached.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PatchMapping
    public ResponseEntity<Map<String, String>> changeCapacity(@RequestBody @Valid SpotDTO spotDTO) {
        Integer monthly = spotDTO.getMonthly();
        Integer oneTime = spotDTO.getOneTime();
        int currentOneTime = parkingSpotService.getAllOneTimeSpots();
        int currentMonthly = parkingSpotService.getAllMonthlySpots();

        Map<String, String> responseMessages = new HashMap<>();

        if (monthly == null && oneTime == null) {
            responseMessages.put("error", "At least one parameter is required.");
            return ResponseEntity.badRequest().body(responseMessages);
        }

        if (oneTime != null) {
            if (oneTime < currentOneTime) {
                responseMessages.put("oneTime", handleDecreaseCapacityOneTime(oneTime));
            } else if (oneTime > currentOneTime) {
                responseMessages.put("oneTime", handleIncreaseCapacityOneTime(oneTime));
            }
        }

        if (monthly != null) {
            if (monthly < currentMonthly) {
                responseMessages.put("monthly", handleDecreaseCapacityMonthly(monthly));
            } else if (monthly > currentMonthly) {
                responseMessages.put("monthly", handleIncreaseCapacityMonthly(monthly));
            }
        }

        return ResponseEntity.ok(responseMessages);
    }

    private String handleDecreaseCapacityMonthly(Integer monthly) {
        Integer totalOccupied = parkingSpotService.getAllMonthlySpots() - parkingSpotService.getAllMonthlySpotsOccupied();

        if (monthly >= totalOccupied) {
            throw new NoSuchAvailableVacancies("Incompatible capacity. Cannot remove occupied spaces");
        }

        List<ParkingSpot> freeSpots = parkingSpotService.getAvalibleSpotM();

        for (int i = 0; i < Math.abs(monthly-totalOccupied); i++) {
            ParkingSpot spotToRemove = freeSpots.get(i);
            parkingSpotService.delete(spotToRemove);
        }

        return "Monthly spots updated. Current total: " + parkingSpotService.getAllMonthlySpots();
    }

    private String handleDecreaseCapacityOneTime(Integer oneTime) {
        Integer totalOccupied = parkingSpotService.getAllOneTimeSpots()-parkingSpotService.getAllOneTimeSpotsOccupied();

        if (oneTime >= totalOccupied) {
            throw new NoSuchAvailableVacancies("Incompatible capacity. Cannot remove occupied spaces");
        }

        List<ParkingSpot> freeSpots = parkingSpotService.getAvalibleSpotO();

        for (int i = 0; i < Math.abs(oneTime-totalOccupied); i++) {
            ParkingSpot spotToRemove = freeSpots.get(i);
            parkingSpotService.delete(spotToRemove);
        }

        return "One-time spots updated. Current total: " + parkingSpotService.getAllOneTimeSpots();
    }

    private String handleIncreaseCapacityOneTime(Integer one) {
        for (int i = parkingSpotService.getAllOneTimeSpots(); i < one; i++) {
            ParkingSpot p = new ParkingSpot();
            p.setMonthly(false);
            p.setOccupied(false);
            parkingSpotService.register(p);
        }
        return "One-time spots increased. Current total: " + parkingSpotService.getAllOneTimeSpots();
    }

    private String handleIncreaseCapacityMonthly(Integer monthly) {
        for (int i = parkingSpotService.getAllMonthlySpots(); i < monthly; i++) {
            ParkingSpot p = new ParkingSpot();
            p.setMonthly(true);
            p.setOccupied(false);
            parkingSpotService.register(p);
        }
        return "Monthly spots increased. Current total: " + parkingSpotService.getAllMonthlySpots();
    }

    private Map<String, Integer> formatter() {
        Map<String, Integer> map = new HashMap<>();

        if(parkingSpotService.getAllMonthlySpots() <= 0 && parkingSpotService.getAllOneTimeSpots() <= 0){
            throw new EntityNotFoundException("No such vacancies available. Start new vacancies.");
        }

        map.put("monthlySpots", parkingSpotService.getAllMonthlySpots());
        map.put("occupiedMonthlySpots", parkingSpotService.getAllMonthlySpotsOccupied());
        map.put("oneTimeSpots", parkingSpotService.getAllOneTimeSpots());
        map.put("occupiedOneTimeSpots", parkingSpotService.getAllOneTimeSpotsOccupied());
        return map;
    }
}