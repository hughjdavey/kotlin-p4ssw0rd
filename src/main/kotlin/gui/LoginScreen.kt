package gui
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.scene.control.PasswordField
import javafx.scene.layout.GridPane
import javafx.util.Duration
import tornadofx.*

/* based on LoginScreen from tornadofx samples */
class LoginScreen : View() {
    override val root = GridPane()
    val appController: AppController by inject()

    var password: PasswordField by singleAssign()

    init {
        title = "Please log in"

        with (root) {
            padding = PasswordApp.APP_INSETS

            row("Password") {
                hgap = 10.0
                password = passwordfield()
                vgap = 10.0
            }

            row {
                button("Login") {
                    isDefaultButton = true

                    setOnAction {
                        appController.tryLogin(password.text)
                    }
                }
            }
        }
    }

    fun clear() {
        password.clear()
    }

    fun shakeStage() {
        var x = 0
        var y = 0
        val cycleCount = 10
        val move = 10
        val keyframeDuration = Duration.seconds(0.04)

        val stage = FX.primaryStage

        val timelineX = Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (x == 0) {
                stage.x = stage.x + move
                x = 1
            } else {
                stage.x = stage.x - move
                x = 0
            }
        }))

        timelineX.cycleCount = cycleCount
        timelineX.isAutoReverse = false

        val timelineY = Timeline(KeyFrame(keyframeDuration, EventHandler {
            if (y == 0) {
                stage.y = stage.y + move
                y = 1;
            } else {
                stage.y = stage.y - move
                y = 0;
            }
        }))

        timelineY.cycleCount = cycleCount;
        timelineY.isAutoReverse = false;

        timelineX.play()
        timelineY.play();
    }
}
