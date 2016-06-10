package gui

import persistence.PasswordEntity
import tornadofx.getProperty
import tornadofx.property

/* based on Customer from tornadofx samples */
class GUIPassword {
    var name by property<String>()
    fun nameProperty() = getProperty(GUIPassword::name)

    var password by property<String>()
    fun passwordProperty() = getProperty(GUIPassword::password)

    var username by property<String>()
    fun usernameProperty() = getProperty(GUIPassword::username)

    var email by property<String>()
    fun emailProperty() = getProperty(GUIPassword::email)

    var url by property<String>()
    fun urlProperty() = getProperty(GUIPassword::url)

    override fun toString() = "Password{$name, $password}"

    fun toPasswordEntity(): PasswordEntity {
        val entity = PasswordEntity();
        entity.name = name;
        entity.password = password;
        entity.username = username;
        entity.email = email;
        entity.url = url;
        return entity
    }

    companion object {
        fun fromPasswordEntity(entity: PasswordEntity): GUIPassword {
            val gui = GUIPassword()
            gui.name = entity.name
            gui.password = entity.password
            gui.username = entity.username
            gui.email = entity.email
            gui.url = entity.url
            return gui;
        }
    }
}