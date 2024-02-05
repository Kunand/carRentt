package com.example.carrent.repository

import com.example.carrent.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface ReservationRepository : JpaRepository<Reservation, Long> {

    @Query("SELECT r.car.id FROM Reservation r WHERE r.startDate <= :endDate AND r.endDate >= :startDate")
    fun findReservedCarIds(@Param("startDate") startDate: LocalDate,
                           @Param("endDate") endDate: LocalDate): List<Long>
}
