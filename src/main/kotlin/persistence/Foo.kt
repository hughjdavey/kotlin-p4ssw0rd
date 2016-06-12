package persistence

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object Foo {
    @Throws(Exception::class)
    @JvmStatic fun main(args: Array<String>) {
        val factory = Configuration().configure().addAnnotatedClass(PasswordEntity::class.java).buildSessionFactory()
        val session = factory.openSession()
        session.beginTransaction()
        val password = PasswordEntity()
        password.name = "Hello"
        password.password = "world"
        session.save(password)
        session.transaction.commit()

        session.beginTransaction()
        val result = session.createQuery("from PasswordEntity").list()
        for (event in result as List<PasswordEntity>) {
            println("Password (" + event.name + ") : " + event.password)
        }
        session.transaction.commit()
        session.close()
    }
}
