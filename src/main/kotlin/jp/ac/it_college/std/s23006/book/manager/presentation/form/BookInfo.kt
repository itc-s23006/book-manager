package jp.ac.it_college.std.s23006.book.manager.presentation.form

import jp.ac.it_college.std.s23006.book.manager.domain.model.Book
import jp.ac.it_college.std.s23006.book.manager.domain.model.BookWithRental
import jp.ac.it_college.std.s23006.book.manager.domain.model.Rental
import jp.ac.it_college.std.s23006.book.manager.infrastructure.database.dao.VBookWithRentalView
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class GetBookListResponse(val bookList: List<BookInfo>)

data class BookInfo(
    val id: Long,
    val title: String,
    val author: String,
    val isRental: Boolean
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.isRental,
    )
}

data class GetBookDetailsResponse(
    val id: Long,
    val title: String,
    val author: String,
    val releaseDate: LocalDate,
    val rentalInfo: RentalInfo?,
) {
    constructor(model: BookWithRental) : this(
        model.book.id,
        model.book.title,
        model.book.author,
        model.book.releaseDate,
        model.rental?.let(::RentalInfo)
    )
}

data class RentalInfo(
    val userId: Long,
    val rentalDateTime: LocalDateTime,
    val returnDeadline: LocalDateTime,
) {
    constructor(model: Rental) : this(
        model.userId,
        model.rentalDateTime,
        model.returnDeadLine
    )
}