package gui

import core.PasswordGenerator
import core.PasswordGenerator.Charset.*
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*

class GenerateScreen : View() {
    override val root = BorderPane()
    val editPasswordScreen: EditPasswordScreen by inject()

    init {
        with (root) {
            padding = PasswordApp.APP_INSETS

            center {
                vbox(spacing = 25.0) {

                    val passwordBox = textfield(){
                        isEditable = false
                    }

                    val letters = checkbox("Letters")
                    val numbers = checkbox("Numbers")
                    val symbols = checkbox("Symbols")

                    val lengthBox = hbox(spacing = 10.0) {
                        alignment = Pos.CENTER
                        label("Length")
                        textfield("12")
                    }

                    hbox() {
                        button("Save") {
                            setOnAction {
                                editPasswordScreen.insertGeneratedPassword(passwordBox.text)
                                closeModal()
                            }
                        }

                        button("Another") {
                            setOnAction {
                                val lengthField = lengthBox.childrenUnmodifiable.filtered { node -> node is TextField }[0] as TextField
                                passwordBox.text = generate(letters.isSelected, numbers.isSelected, symbols.isSelected, lengthField.text.toInt())
                            }
                        }
                    }
                }
            }
        }
    }

    /* get a random password based on the user's choice of length and character sets */
    private fun generate(letters: Boolean, numbers: Boolean, symbols: Boolean, length: Int): String {
        val charsets = (listOf(ALPHA, NUMBER, SYMBOL) zip listOf(letters, numbers, symbols))
                .filter { pair -> pair.second == true }
                .map { pair -> pair.first }
                .toSet()

        return PasswordGenerator.generatePassword(length, charsets).joinToString("")
    }
}