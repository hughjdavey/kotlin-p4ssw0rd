package gui

import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import tornadofx.*

class SearchScreen : View() {
    override val root = GridPane()
    val appController: AppController by inject()

    var searchField: TextField by singleAssign()

    init {
        with (root) {
            padding = PasswordApp.APP_INSETS

            row() {
                hgap = 10.0
                searchField = textfield()
                vgap = 10.0
            }

            row {
                button("Search") {
                    isDefaultButton = true

                    setOnAction {
                        appController.doSearch(searchField.text)
                    }
                }
            }
        }
    }

    fun clear() {
        searchField.clear()
    }
}