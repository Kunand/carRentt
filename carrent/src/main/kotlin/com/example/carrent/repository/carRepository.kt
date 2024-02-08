package com.example.carrent.repository

import com.example.carrent.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate


interface CarRepository : JpaRepository<Car, Long> {
    @Query("SELECT c FROM Car c WHERE c.id NOT IN (:reservedCarIds)")
    fun findAvailableCars(@Param("reservedCarIds") reservedCarIds: List<Long>): List<Car>
}




