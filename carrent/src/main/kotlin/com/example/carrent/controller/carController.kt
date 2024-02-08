package com.example.carrent.controller

import com.example.carrent.service.CarService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalDate

@Controller
@RequestMapping("/")
class CarController(private val carService: CarService) {

    @GetMapping
    fun showDatePicker(model: Model): String {
        return "datePicker"
    }

    @GetMapping("/carsList")
    fun carsList(
            @RequestParam("startDate") startDate: String,
            @RequestParam("endDate") endDate: String,
            model: Model
    ): String {
        val start = LocalDate.parse(startDate)
        val end = LocalDate.parse(endDate)
        val availableCars = carService.findAvailableCars(start, end)
        model.addAttribute("cars", availableCars)
        model.addAttribute("startDate", startDate)
        model.addAttribute("endDate", endDate)
        return "carsList" // A Thymeleaf template, ami megjeleníti az autókat
    }

    @GetMapping("/reserve")
    fun showReservationForm(
            @RequestParam("carId") carId: Long,
            @RequestParam("startDate") startDate: String,
            @RequestParam("endDate") endDate: String,
            model: Model
    ): String {
        model.addAttribute("carId", carId)
        model.addAttribute("startDate", startDate)
        model.addAttribute("endDate", endDate)
        return "reserve" // A Thymeleaf sablon neve
    }


//    @PostMapping("/submitReservation")
//    fun submitReservation(
//            @RequestParam carId: Long,
//            @RequestParam startDate: String,
//            @RequestParam endDate: String,
//            redirectAttributes: RedirectAttributes
//    ): String {
//        val start = LocalDate.parse(startDate)
//        val end = LocalDate.parse(endDate)
//        try {
//            carService.reserveCar(carId, start, end)
//            redirectAttributes.addFlashAttribute("successMessage", "Az autó sikeresen lefoglalva.")
//        } catch (e: Exception) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Foglalási hiba: ${e.message}")
//        }
//        return "redirect:/cars" // Visszairányítás az autók listájához vagy egy sikeres foglalást jelző oldalra
//    }
}















//import com.example.carrent.service.CarService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Controller
//import org.springframework.ui.Model
//import org.springframework.web.bind.annotation.*
//import java.time.LocalDate


//@Controller
//class CarController(private val carService: CarService) {
//
//        @GetMapping("/")
//        fun searchCars(
//                @RequestParam("startDate") startDate: String,
//                @RequestParam("endDate") endDate: String,
//                model: Model
//        ): String {
//            val start = LocalDate.parse(startDate)
//            val end = LocalDate.parse(endDate)
//            val availableCars = carService.findAvailableCars(start, end) // Javítva
//            model.addAttribute("cars", availableCars)
//            return "cars"
//        }
//    }
//




//
//    @GetMapping("/")
//    fun listCars(model: Model): String {
//        val cars = carService.findAllCars()
//        println(cars)
//        model.addAttribute("cars", cars)
//        return "cars" // Ez a Thymeleaf sablon neve
//    }
//    @GetMapping("/searchCars")
//    fun searchCars(
//            @RequestParam("startDate") startDate: String,
//            @RequestParam("endDate") endDate: String,
//            model: Model
//    ): String {
//        val start = LocalDate.parse(startDate)
//        val end = LocalDate.parse(endDate)
//        val availableCars = CarService.findAvailableCars(start, end)
//        model.addAttribute("cars", availableCars)
//        return "carList" // A Thymeleaf template, ami megjeleníti az autókat
//    }


//@Controller
//class MyController(val carService: CarService) {
//
//    @PostMapping("/submitDateRange")
//    fun submitDateRange(@ModelAttribute dateRange: DateRange): String {
//        // Itt kezeld a dátumtartományt
//        println("Start Date: ${dateRange.startDate}")
//        println("End Date: ${dateRange.endDate}")
//
//        return "resultPage" // Visszatérési nézet
//    }




