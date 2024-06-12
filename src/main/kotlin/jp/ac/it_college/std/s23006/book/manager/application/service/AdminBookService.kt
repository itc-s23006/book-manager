package jp.ac.it_college.std.s23006.book.manager.application.service

import jp.ac.it_college.std.s23006.book.manager.domain.model.Book
import jp.ac.it_college.std.s23006.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s23006.book.manager.presentation.exception.BookIdAlreadyUsedException
import jp.ac.it_college.std.s23006.book.manager.presentation.exception.BookNotFoundException
import jp.ac.it_college.std.s23006.book.manager.presentation.form.UpdateBookRequest
import kotlinx.datetime.LocalDate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody

@Service
class AdminBookService(
    private val bookRepository: BookRepository
) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let {
            throw BookIdAlreadyUsedException("既に存在する書籍ID: ${book.id}")
        }
        bookRepository.register(book)
    }

    @Transactional
    fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookRepository.findWithRental(id) ?: throw BookNotFoundException("存在しない書籍ID: $id")

        bookRepository.update(id, title, author, releaseDate)
    }

    @Transactional
    fun delete(id: Long) {

        bookRepository.findWithRental(id) ?: throw BookNotFoundException("存在しない書籍ID: $id")

        bookRepository.delete(id)
    }
}