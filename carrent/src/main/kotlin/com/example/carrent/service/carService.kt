
package com.example.carrent.service

import com.example.carrent.model.Car
import com.example.carrent.model.Reservation
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
            return carRepository.findAll()
        }
        return carRepository.findAvailableCars(reservedCarIds)
    }
    fun reserveCar(carId: Long, startDate: LocalDate, endDate: LocalDate) {
        val car = carRepository.findById(carId).orElseThrow { IllegalArgumentException("Nem található autó ezzel az ID-val: $carId") }
        val reservation = Reservation(car = car, startDate = startDate, endDate = endDate)
        reservationRepository.save(reservation)
    }

}










