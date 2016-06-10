package gui

import javafx.scene.control.TextField
import org.controlsfx.control.Notifications
import tornadofx.*

class EditPasswordScreen : View() {
    override val root = Form()
    val appController: AppController by inject()
    val generateScreen: GenerateScreen by inject()

    var nameField: TextField by singleAssign()
    var passwordField: TextField by singleAssign()
    val password = appController.getEditPassword()

    init {
        with (root) {
            minHeight = PasswordApp.APP_HEIGHT
            minWidth = PasswordApp.APP_WIDTH

            fieldset() {
                field("Name") {
                    nameField = textfield() { bind(password.nameProperty()) }
                }
                field("Password") {
                    passwordField = textfield() { bind(password.passwordProperty()) }
                }
                field("Username") {
                    textfield().bind(password.usernameProperty())
                }
                field("Email") {
                    textfield().bind(password.emailProperty())
                }
                field("URL") {
                    textfield().bind(password.urlProperty())
                }

                spacing = 20.0
                padding = PasswordApp.APP_INSETS
            }

            button("Generate") {
                setOnAction {
                    generateScreen.openModal()
                }
                setOnKeyPressed(PasswordApp.enterKeyHandler)
            }


            button("Save") {
                setOnAction {
                    Notifications.create()
                            .title("Password saved!")
                            .text("Password savecxzd!")
                            .owner(this)
                            .showInformation()
                }

                // disable save button until at least name and password fields are filled
                disableProperty().bind(nameField.textProperty().isEmpty.or(passwordField.textProperty()?.isEmpty))
                setOnKeyPressed(PasswordApp.enterKeyHandler)
            }
        }
    }

    /* paste a generated password into the password field */
    fun insertGeneratedPassword(password: String) {
        passwordField.text = password
    }
}