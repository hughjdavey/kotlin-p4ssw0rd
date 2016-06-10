package gui

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font
import tornadofx.*

class HomeScreen : View() {
    override val root = BorderPane()
    val appController: AppController by inject()

    init {
        with (root) {
            padding = PasswordApp.APP_INSETS
            setPrefSize(PasswordApp.APP_WIDTH, PasswordApp.APP_HEIGHT)

            top {
                label("Welcome to P4$\$w0rd") {
                    font = Font.font(22.0)
                }
            }

            center {
                vbox(spacing = 25.0) {
                    padding = Insets(100.0, 0.0, 0.0, 0.0)

                    button("New") {
                        setOnAction {
                            appController.showEditPasswordScreen()
                        }
                        setOnKeyPressed(PasswordApp.enterKeyHandler)
                    }

                    button("Search") {
                        setOnAction {
                            appController.showSearchScreen()
                        }
                        setOnKeyPressed(PasswordApp.enterKeyHandler)
                    }

                    button("View All") {
                        setOnAction {
                            appController.showViewPasswordsScreen()
                        }
                        setOnKeyPressed(PasswordApp.enterKeyHandler)
                    }

                    button("Exit") {
                        setOnAction {
                            Platform.exit()
                        }
                        setOnKeyPressed(PasswordApp.enterKeyHandler)
                    }

                    button("Change App Password") {
                        setOnAction {
                            appController.showChangePasswordScreen()
                        }
                        setOnKeyPressed(PasswordApp.enterKeyHandler)
                    }
                }
            }
        }
    }
}