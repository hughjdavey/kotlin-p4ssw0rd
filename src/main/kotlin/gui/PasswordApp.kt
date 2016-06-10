package gui
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import tornadofx.App
import tornadofx.importStylesheet

class PasswordApp : App() {
    override val primaryView = LoginScreen::class
    val appController: AppController by inject()

    override fun start(stage: Stage) {
        super.start(stage)
        appController.init()
    }

    companion object {
        val APP_HEIGHT = 600.0
        val APP_WIDTH = 800.0
        val APP_INSETS = Insets(25.0)

        /* handler to allow hitting the enter key to fire a button (only spacebar has this by default) */
        val enterKeyHandler = { evt: KeyEvent ->
            if (evt.code == KeyCode.ENTER)
                (evt.source as Button).fire()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            launch(PasswordApp::class.java)
        }
    }
}