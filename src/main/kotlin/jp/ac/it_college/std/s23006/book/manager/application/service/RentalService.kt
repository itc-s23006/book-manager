package jp.ac.it_college.std.s23006.book.manager.application.service

import jp.ac.it_college.std.s23006.book.manager.domain.exception.BookNotAvailableException
import jp.ac.it_college.std.s23006.book.manager.domain.exception.RentalStateException
import jp.ac.it_college.std.s23006.book.manager.domain.model.Rental
import jp.ac.it_college.std.s23006.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s23006.book.manager.domain.repository.RentalRepository
import jp.ac.it_college.std.s23006.book.manager.domain.repository.UserRepository
import kotlinx.datetime.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val RENTAL_TERM_DAYS = 14L

@Service
class RentalService(
    private val userRepository: UserRepository,
    private val bookRepository: BookRepository,
    private val rentalRepository: RentalRepository
) {
    @Transactional
    fun startRental(bookId: Long, userId: Long) {

        userRepository.find(userId)
            ?: throw RentalStateException("該当するユーザがいません")

        val book = bookRepository.findWithRental(bookId)
            ?: throw RentalStateException("該当する書籍がありません")

        if (book.isRental) {
            throw BookNotAvailableException("貸し出し中です")
        }

        val current = Clock.System.now()

        val rentalDatetime = current.toLocalDateTime(TimeZone.currentSystemDefault())

        val returnDeadline = current.plus(
            RENTAL_TERM_DAYS, DateTimeUnit.DAY, TimeZone.currentSystemDefault()
        ).toLocalDateTime(TimeZone.currentSystemDefault())
        val rental = Rental(bookId, userId, rentalDatetime, returnDeadline)

        rentalRepository.startRental(rental)
    }
}