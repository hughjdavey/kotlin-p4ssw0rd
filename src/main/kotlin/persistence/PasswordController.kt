package persistence

import javafx.collections.FXCollections
import javafx.collections.ObservableList

class PasswordController {
    private val passwordService = PasswordService()
    private var passwordList = FXCollections.observableArrayList<PasswordEntity>()

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
