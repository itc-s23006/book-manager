package jp.ac.it_college.std.s23006.book.manager.presentation.controller

import jp.ac.it_college.std.s23006.book.manager.application.service.RentalService
import jp.ac.it_college.std.s23006.book.manager.application.service.security.BookManagerUserDetailsService
import jp.ac.it_college.std.s23006.book.manager.presentation.form.RentalStartRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rental")
@CrossOrigin
class RentalController(
    private val rentalService: RentalService
) {
    @PostMapping("/start")
    fun startRental(
        @RequestBody request: RentalStartRequest,
        @AuthenticationPrincipal user: BookManagerUserDetailsService.BookManagerUserDetails
    ) {
        rentalService.startRental(request.bookId, user.id)
    }
}