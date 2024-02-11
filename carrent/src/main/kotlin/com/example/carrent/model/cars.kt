package com.example.carrent.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "cars")
data class Car(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @Column(nullable = false)
        val make: String,

        @Column(nullable = false)
        val model: String,
        @Column(nullable = false)
        val price: BigDecimal = BigDecimal.ZERO,

        @Column(nullable = false)
        var isactive: Boolean,

        @OneToMany(mappedBy = "car", cascade = [CascadeType.ALL], orphanRemoval = true)
        val reservations: List<Reservation> = mutableListOf())


        val imageUrl: String? = null

