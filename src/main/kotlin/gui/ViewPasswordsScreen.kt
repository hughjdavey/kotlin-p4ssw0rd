package gui
import javafx.event.Event
import javafx.scene.control.TableView
import javafx.scene.input.*
import javafx.scene.layout.BorderPane
import org.controlsfx.glyphfont.FontAwesome
import persistence.PasswordEntity
import tornadofx.*

class ViewPasswordsScreen : View() {
    override val root = BorderPane()
    val appController: AppController by inject()
    val passwordList = appController.getPasswordSearchResults()

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
            menubar {
                menu("Navigation") {
                    menuitem("Home", KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN), FontAwesome().create(FontAwesome.Glyph.HOME.char)) {
                        appController.showHomeScreen()
                    }
                }
            }

            minHeight = PasswordApp.APP_HEIGHT
            minWidth = PasswordApp.APP_WIDTH

            center {
                tableview(passwordList) {
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