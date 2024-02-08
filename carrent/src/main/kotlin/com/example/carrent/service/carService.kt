
package com.example.carrent.service

import com.example.carrent.model.Car
import com.example.carrent.repository.CarRepository
import com.example.carrent.repository.ReservationRepository
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class CarService(private val carRepository: CarRepository,
                 private val reservationRepository: ReservationRepository) {

    fun findAvailableCars(startDate: LocalDate, endDate: LocalDate): List<Car> {
        val reservedCarIds = reservationRepository.findReservedCarIds(startDate, endDate)
        if (reservedCarIds.isEmpty()) {
            // Ha nincsenek foglalt autók, visszaadhatjuk az összes autót.
            return carRepository.findAll()
        }
        // Kizárjuk a foglalt autókat az ID-juk alapján.
        return carRepository.findAvailableCars(reservedCarIds)
    }

}







