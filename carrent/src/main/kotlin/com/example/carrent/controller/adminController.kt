package com.example.carrent.controller


import com.example.carrent.model.Car
import com.example.carrent.service.AdminService

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.math.BigDecimal


@Controller
@RequestMapping("/adminDashboard")
class AdminController(private val adminService: AdminService) {

    @GetMapping
    fun adminDashboard(model: Model): String {
        val cars = adminService.findAllCars()
        val reservations = adminService.findAllReservations()

        // Hozzáadjuk őket a modellhez
        model.addAttribute("cars", cars)
        model.addAttribute("reservations", reservations)

        return "adminDashboard"
    }

    @PostMapping("/toggleActiveStatus")
    fun toggleActiveStatus(@RequestParam("carId") carId: Long, redirectAttributes: RedirectAttributes): String {
        adminService.toggleCarActiveStatus(carId)

        redirectAttributes.addFlashAttribute("successMessage", "Autó státusza sikeresen frissítve.")

        return "redirect:/adminDashboard"
    }

    @GetMapping("/addNewCarForm")
    fun showAddNewCarForm(model: Model): String {
        return "addNewCarForm"
    }

    @PostMapping("/addNewCarForm")
    fun addNewCar(@RequestParam("make") make: String,
                  @RequestParam("model") model: String,
                  @RequestParam("price") price: BigDecimal,
                  @RequestParam("isactive") isactive: Boolean,
                  redirectAttributes: RedirectAttributes): String {
        adminService.addNewCar(make, model, price, isactive)
        redirectAttributes.addFlashAttribute("successMessage", "Új autó sikeresen hozzáadva.")
        return "redirect:/adminDashboard"
    }

    @GetMapping("/editCarForm")
    fun editCarForm(@RequestParam("carId") carId: Long, model: Model): String {
        val car = adminService.findCarById(carId)
        model.addAttribute("car", car)
        return "editCarForm"
    }


    @PostMapping("/editCarForm")
    fun updateCar(@ModelAttribute car: Car, redirectAttributes: RedirectAttributes): String {
        adminService.updateCar(car)
        redirectAttributes.addFlashAttribute("successMessage", "Autó frissítve.")
        return "redirect:/adminDashboard"
    }




}
