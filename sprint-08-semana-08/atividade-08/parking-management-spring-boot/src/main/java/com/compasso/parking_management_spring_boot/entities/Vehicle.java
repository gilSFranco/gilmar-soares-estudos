package com.compasso.parking_management_spring_boot.entities;

import com.compasso.parking_management_spring_boot.entities.enums.Category;
import com.compasso.parking_management_spring_boot.entities.enums.TypeVehicle;
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
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeVehicle type;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "payment_date", nullable = true)
    private LocalDateTime paymentDate;

    @Column(name = "register")
    private Boolean register;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return type == vehicle.type && category == vehicle.category && Objects.equals(licensePlate, vehicle.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, category, licensePlate);
    }

    public Vehicle (String lisencePlate, TypeVehicle type){
        this.licensePlate = lisencePlate;
        this.type = type;
        this.register = false;
        if(this.type == TypeVehicle.PUBLICSERVICE){
            this.category = Category.PUBLIC;
        }
        else{
            this.category = Category.ONETIME;
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type=" + type +
                ", category=" + category +
                ", licensePlate='" + licensePlate + '\'' +
                ", paymentDate=" + paymentDate +
                ", register=" + register +
                '}';
    }
}