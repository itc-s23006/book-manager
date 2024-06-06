package jp.ac.it_college.std.s23006.book.manager.presentation.controller

import jp.ac.it_college.std.s23006.book.manager.application.service.BookService
import jp.ac.it_college.std.s23006.book.manager.presentation.form.GetBookListResponse
import jp.ac.it_college.std.s23006.book.manager.presentation.form.BookInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController(
    private val bookService: BookService,
) {
    @GetMapping("/list")
    fun getlist(): GetBookListResponse {
        val bookList = bookService.getlist().map(::BookInfo)
        return GetBookListResponse(bookList)
    }
}