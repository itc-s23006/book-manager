package jp.ac.it_college.std.s23006.book.manager.infrastructure.database.repository

import jp.ac.it_college.std.s23006.book.manager.domain.model.User
import jp.ac.it_college.std.s23006.book.manager.domain.repository.UserRepository
import jp.ac.it_college.std.s23006.book.manager.infrastructure.database.dao.UserEntity
import jp.ac.it_college.std.s23006.book.manager.infrastructure.databese.dao.UserTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun find(email: String): User? {
        return transaction {
            val entity = UserEntity.find {
                UserTable.email eq email
            }.singleOrNull()
            entity?.let(::toModel)
        }
    }

    private fun toModel(user: UserEntity) = User(
        id = user.id.value,
        email = user.email,
        password = user.password,
        name = user.name,
        roleType = user.roleType
    )
}