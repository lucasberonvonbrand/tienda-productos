package com.example.sale_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sale_id;

    private Long cart_id;

    private LocalDate date;

    private double total;


}
