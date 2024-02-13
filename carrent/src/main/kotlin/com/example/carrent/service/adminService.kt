package com.example.carrent.service


import com.example.carrent.model.Car
import com.example.carrent.model.Reservation
import com.example.carrent.repository.CarRepository
import com.example.carrent.repository.ReservationRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AdminService(private val carRepository: CarRepository,
                   private val reservationRepository: ReservationRepository) {

    fun findAllReservations(): List<Reservation> {
        return reservationRepository.findAll()
    }

    fun findCarById(carId: Long): Car {
        return carRepository.findById(carId).orElseThrow {
            NoSuchElementException("No car with id: $carId")

        }
    }

    fun updateCar(car: Car) {
        carRepository.save(car) // Ez automatikusan frissíti az autót, ha az `id` már létezik
    }


    fun findAllCars(): List<Car> {
        return carRepository.findAll()
    }

    fun toggleCarActiveStatus(carId: Long) {
        val car = carRepository.findById(carId)
        car.ifPresent {
            it.isactive = it.isactive?.not() ?: true // Ha a car.isActive null, akkor alapértelmezetten 'true'-ra állítjuk
            carRepository.save(it)
        }
    }

    fun addNewCar(make: String, model: String, price: BigDecimal, isActive: Boolean) {
        val car = Car(make = make, model = model, price = price, isactive = isActive)
        carRepository.save(car)
    }



    // További metódusok az autók szerkesztéséhez, új autó felviteléhez stb.
}
