package persistence

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest

class PasswordController {
    private val passwordService = PasswordService()
    private var passwordList = FXCollections.observableArrayList<PasswordEntity>()

    fun validatePassword(password: String): Boolean {
        return passwordService.getAppPassword() == toHash(password);
    }

    fun setNewPassword(password: String) {
        return passwordService.setAppPassword(toHash(password))
    }

    private fun toHash(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256");
        digest.update(password.toByteArray());
        return String.format("%064x", BigInteger(1, digest.digest()));
    }

    fun addPassword(password: PasswordEntity) {
        passwordService.addPassword(password)
    }

    fun getPasswordList(): ObservableList<PasswordEntity> {
        if (!passwordList.isEmpty()) passwordList.clear()
        passwordList = FXCollections.observableList(passwordService.listPasswords())
        return passwordList
    }

    fun getPasswordList(search: String): ObservableList<PasswordEntity> {
        if (!passwordList.isEmpty()) passwordList.clear()
        passwordList = FXCollections.observableList(passwordService.listPasswords(search))
        return passwordList
    }

    fun removePassword(id: Long) {
        passwordService.removePassword(id)
    }

    fun updatePassword(password: PasswordEntity) {
        passwordService.updatePassword(password)
    }
}
