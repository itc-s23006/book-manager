package jp.ac.it_college.std.s23006.book.manager.domain.repository

import jp.ac.it_college.std.s23006.book.manager.domain.model.BookWithRental

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
}