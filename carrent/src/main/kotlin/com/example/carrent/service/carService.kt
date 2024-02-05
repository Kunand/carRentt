
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



//@Service
//class CarService(private val carRepository: CarRepository,
//                 private val reservationRepository: ReservationRepository) {
//
//    fun findAvailableCars(startDate: LocalDate, endDate: LocalDate): List<Car> {
//        // Itt egy egyszerűsített logika, ami feltételezi, hogy a ReservationRepository
//        // rendelkezik egy olyan metódussal, ami visszaadja a foglalt autók ID-jait az adott időszakra.
//        val reservedCarIds = reservationRepository.findReservedCarIds(startDate, endDate)
//        // Kizárjuk a foglalt autókat, és visszaadjuk az elérhető autók listáját.
//        return carRepository.findAllByIdNotIn(reservedCarIds)
//    }
//
//    // Esetleges további szolgáltatások és üzleti logika
//}










//package com.example.carrent.service
//
//import com.example.carrent.model.Car
//import com.example.carrent.repository.CarRepository
//import org.springframework.stereotype.Service
//import java.time.LocalDate
//
//@Service
//class CarService(private val carRepository: CarRepository) {
//    fun findAllCars(): List<Car> {
//        return carRepository.findAll()
//    }
//
//    fun findAvailableCars(startDate: LocalDate, endDate: LocalDate): List<Car?>? {
//        // Logika, hogy meghatározd, mely autók szabadok a kívánt időszakban
//        // Ez magában foglalhat egy adatbázis lekérdezést vagy egy meglévő gyűjtemény szűrését
//        return carRepository.findAvailableCars(startDate, endDate)
//    }
//
//
//}