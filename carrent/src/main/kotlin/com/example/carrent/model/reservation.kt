package com.example.carrent.model

import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import java.time.LocalDate
import jakarta.persistence.*

@Entity
class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cars_id")
    private val car: Car? = null

    private val startDate: LocalDate? = null
    private val endDate: LocalDate? = null // Standard getters és setters...
}
