package jp.ac.it_college.std.s23006.book.manager.application.service

import jp.ac.it_college.std.s23006.book.manager.domain.model.BookWithRental
import jp.ac.it_college.std.s23006.book.manager.domain.repository.BookRepository
import jp.ac.it_college.std.s23006.book.manager.domain.exception.BookNotFoundException
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {
    fun getlist(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: Long) : BookWithRental {
        return bookRepository.findWithRental(bookId)
            ?: throw BookNotFoundException("存在しない書籍ID: $bookId")
    }
}