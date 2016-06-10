package gui
import javafx.collections.FXCollections
import javafx.event.Event
import javafx.scene.control.TableView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import persistence.PasswordEntity
import persistence.PasswordStorage
import tornadofx.*

class AllPasswordsScreen : View() {
    override val root = BorderPane()
    val appController: AppController by inject()
    val passwords = PasswordStorage.samplePasswordList();

    /* allows user press the enter key on a row to edit that password */
    val enterKeyHandler = { evt: KeyEvent ->
        if (evt.code == KeyCode.ENTER) {
            getSelectedPassword(evt)
        }
    }

    /* allows user double click on a row to edit that password */
    val doubleClickHandler = { evt: MouseEvent ->
        if (evt.clickCount == 2) {
            getSelectedPassword(evt)
        }
    }

    init {
        with(root) {
            minHeight = PasswordApp.APP_HEIGHT
            minWidth = PasswordApp.APP_WIDTH

            center {
                tableview(FXCollections.observableArrayList<PasswordEntity>(passwords)) {
                    column("Name", PasswordEntity::name)
                    column("Password", PasswordEntity::password)
                    column("Username", PasswordEntity::username)
                    column("Email Address", PasswordEntity::email)
                    column("URL", PasswordEntity::url)

                    resizeColumnsToFitContent()
                    setOnMouseClicked(doubleClickHandler)
                    setOnKeyPressed(enterKeyHandler)
                }
            }
        }
    }

    private fun getSelectedPassword(evt: Event) {
        val password = (evt.source as TableView<PasswordEntity>).selectionModel.selectedItem
        appController.showEditPasswordScreen(password)
    }
}