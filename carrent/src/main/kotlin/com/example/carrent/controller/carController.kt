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
        return "reserve"
    }

    @PostMapping("/submitReservation")
    fun submitReservation(
            @RequestParam("carId") carId: Long,
            @RequestParam("startDate") startDate: String,
            @RequestParam("endDate") endDate: String,
            model: Model
    ): String {
        println(startDate)
        val start = LocalDate.parse(startDate)
        val end = LocalDate.parse(endDate)
        println(start)
        println(end)
        // Itt hívd meg a service rétegben lévő reserveCar metódust
        carService.reserveCar(carId, start, end)

        // Sikeres foglalás után adj hozzá attribútumokat a modellhez, ha szükséges
        model.addAttribute("carId", carId)
        model.addAttribute("startDate", startDate)
        model.addAttribute("endDate", endDate)
        return "successReserve" // Átirányítás a sikeres foglalást jelző oldalra
    }
}



