package jp.ac.it_college.std.s23006.book.manager.domain

import jp.ac.it_college.std.s23006.book.manager.domain.model.User

interface UserRepository {
    fun find(email: String): User?
}