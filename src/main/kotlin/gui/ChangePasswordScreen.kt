package gui

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import java.nio.file.Files as JavaFiles;
import java.nio.file.Paths as JavaPaths;
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import tornadofx.*

class ChangePasswordScreen : View() {
    override val root = GridPane()
    val appController: AppController by inject()

    var password1: PasswordField by singleAssign()
    var password2: PasswordField by singleAssign()
    var comparisonText: Label by singleAssign()
    var submit: Button by singleAssign()

    init {
        with (root) {
            padding = PasswordApp.APP_INSETS

            row("Enter Password") {
                hgap = 10.0
                password1 = passwordfield() {
                    textProperty().addListener { observable, oldValue, newValue -> comparePasswords() }
                }
                vgap = 10.0
            }

            row("Confirm Password") {
                hgap = 10.0
                password2 = passwordfield() {
                    textProperty().addListener { observable, oldValue, newValue -> comparePasswords() }
                }
                vgap = 10.0
            }

            row {
                comparisonText = label {
                    minWidth = 200.0
                    font = Font.font("System", FontWeight.BOLD, 12.0)
                }
            }

            row {
                submit = button("Submit") {
                    setOnAction {
                        HashUtil.setNewPassword(password1.text)
                        appController.showHomeScreen()

                    }
                    setOnKeyPressed(PasswordApp.enterKeyHandler)
                }
            }
        }
    }

    /* called whenever either textbox changes and sets the status string telling the user if the passwords match, and enables submission if they are */
    fun comparePasswords() {
        val match = password1.text == password2.text
        comparisonText.text = "Passwords " + (if (match) "" else "do not ") + "match"
        comparisonText.textFill = if (match) Color.GREEN else Color.RED
        submit.isDisable = !match;
    }
}