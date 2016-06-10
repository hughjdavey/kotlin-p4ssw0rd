package persistence

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object PasswordStorage {
    private val sessionFactory: SessionFactory

    init {
        try {
            sessionFactory = Configuration().configure().addAnnotatedClass(PasswordEntity::class.java).buildSessionFactory()
        } catch (ex: Throwable) {
            System.err.println("Initial SessionFactory creation failed." + ex)
            throw ExceptionInInitializerError(ex)
        }

    }

    fun openSession(): Session {
        return sessionFactory.openSession()
    }
}
