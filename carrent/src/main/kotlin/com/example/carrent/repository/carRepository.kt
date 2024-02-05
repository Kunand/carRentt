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




//interface CarRepository : JpaRepository<Car, Long> {
//    fun findAllByIdNotIn(reservedCarIds: List<Long>): List<Car>
//}





//interface CarRepository : JpaRepository<Car, Long> {
//    // Itt további egyéni lekérdezéseket adhatsz hozzá szükség szerint
//    @Query("SELECT c FROM Car c WHERE c.id NOT IN (" +
//            "SELECT r.car.id FROM Reservation r WHERE " +
//            "r.startDate <= :endDate AND r.endDate >= :startDate)")
//    fun findAvailableCars(@Param("startDate") startDate: LocalDate,
//                          @Param("endDate") endDate: LocalDate): List<Car>

//    @Repository
//    interface CarRepository : JpaRepository<Car, Long> {
//        @Query("SELECT c FROM Car c WHERE c.id NOT IN (" +
//                "SELECT r.car.id FROM Reservation r WHERE " +
//                "r.startDate <= :endDate AND r.endDate >= :startDate)")
//        fun findAvailableCars(@Param("startDate") startDate: LocalDate,
//                              @Param("endDate") endDate: LocalDate): List<Car>
//    }
