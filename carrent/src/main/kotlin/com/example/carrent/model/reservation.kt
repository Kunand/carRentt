package com.example.carrent.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "reservation")
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "cars_id")
        val car: Car? = null,

        val startDate: LocalDate? = null,

        val endDate: LocalDate? = null
)
