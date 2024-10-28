package com.compasso.parking_management_spring_boot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "exit_date", nullable = true)
    private LocalDateTime exitDate;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "entry_gate", nullable = false)
    private Integer entryGate;

    @Column(name = "exit_gate", nullable = true)
    private Integer exitGate;

    @Column(name = "value", nullable = true)
    private Double value;

    private Boolean parked;

    private String spots;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Ticket(Vehicle vehicle, LocalDateTime entryDate, Integer entryGate, String spots){
        this.vehicle = vehicle;
        this.entryDate = entryDate;
        this.entryGate = entryGate;
        this.parked = true;
        this.spots = spots;
        this.exitDate = null;
        this.exitGate = null;
        this.value = null;
    }
}
