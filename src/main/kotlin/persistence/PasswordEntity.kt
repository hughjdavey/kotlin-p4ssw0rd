package persistence

import core.PasswordGenerator
import javax.persistence.*

@Entity
@Table(name = "PASSWORDS")
class PasswordEntity {
    @Id
    @GeneratedValue
    @Column
    var id: Long = 0

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var password: String? = null

    @Column()
    var username: String? = null

    @Column
    var email: String? = null

    @Column
    var url: String? = null

    fun testPassword(name: String): PasswordEntity {
        val password = PasswordEntity()
        password.name = name
        password.password = PasswordGenerator.generatePassword().joinToString("")
        password.username = if (Math.random() < 0.5) "jbloggs1984" else null
        password.email = if (Math.random() < 0.5) "jbloggs1984@gmail.com" else null
        password.url = if (Math.random() < 0.6) "https://www.${name.toLowerCase().replace(" ", "")}.com/" else null
        return password
    }

    override fun toString(): String{
        return "Password(name=$name, password=$password, username=$username, email=$email, url=$url)"
    }
}
