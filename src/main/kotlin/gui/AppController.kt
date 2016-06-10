package gui

import javafx.application.Platform
import persistence.PasswordEntity
import tornadofx.Controller
import tornadofx.FX

/* based on LoginController from tornadofx samples */
class AppController : Controller() {
    val allPasswordsScreen: AllPasswordsScreen by inject()
    val changePasswordScreen: ChangePasswordScreen by inject()
    val editScreen: EditPasswordScreen by inject()
    val homeScreen: HomeScreen by inject()
    val loginScreen: LoginScreen by inject()
    val searchScreen: SearchScreen by inject()

    fun init() {
        with (config) {
            if (containsKey(PASSWORD))
                tryLogin(string(PASSWORD))
            else
                showLoginScreen("Please log in")
        }
    }

    fun showLoginScreen(message: String, shake: Boolean = false) {
        if (FX.primaryStage.scene.root != loginScreen.root) {
            FX.primaryStage.scene.root = loginScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = message

        Platform.runLater {
            loginScreen.password.requestFocus()
            if (shake) loginScreen.shakeStage()
        }
    }

    var passwordToEdit: PasswordEntity? = null;

    /* returns a password object to edit if one has been selected (otherwise we are making a new password and so return an empty one) */
    fun getEditPassword(): GUIPassword {
        var password = GUIPassword()
        if (passwordToEdit != null) {
            password = GUIPassword.fromPasswordEntity(passwordToEdit as PasswordEntity)
            passwordToEdit = null
        }
        return password
    }

    fun showEditPasswordScreen(password: PasswordEntity? = null) {
        val title = if (password != null) {
            passwordToEdit = password
            "Edit";
        }
        else "New"

        if (FX.primaryStage.scene.root != editScreen.root) {
            FX.primaryStage.scene.root = editScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = title + " Password"
    }

    fun showViewAllScreen() {
        if (FX.primaryStage.scene.root != allPasswordsScreen.root) {
            FX.primaryStage.scene.root = allPasswordsScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = "All Passwords"
    }

    fun showSearchScreen() {
        if (FX.primaryStage.scene.root != searchScreen.root) {
            FX.primaryStage.scene.root = searchScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = "Find Passwords"
    }

    fun showHomeScreen() {
        if (FX.primaryStage.scene.root != homeScreen.root) {
            FX.primaryStage.scene.root = homeScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = "P4$\$w0rd"
    }

    fun showChangePasswordScreen() {
        if (FX.primaryStage.scene.root != changePasswordScreen.root) {
            FX.primaryStage.scene.root = changePasswordScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = "Change Password"
    }

    fun tryLogin(password: String) {
        runAsync {
            HashUtil.passwordValid(password)
        } ui { successfulLogin ->
            loginScreen.clear()

            if (successfulLogin) {
                showHomeScreen()
            } else {
                showLoginScreen("Login failed. Please try again.", true)
            }
        }
    }

    companion object {
        val PASSWORD = "password"
    }
}