package gui

import javafx.application.Platform
import javafx.collections.ObservableList
import persistence.PasswordController
import persistence.PasswordEntity
import tornadofx.Controller
import tornadofx.FX

/* based on LoginController from tornadofx samples */
class AppController : Controller() {
    val viewPasswordsScreen: ViewPasswordsScreen by inject()
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

    private var passwordToEdit: PasswordEntity? = null;
    /* returns a password object to edit if one has been selected (otherwise we are making a new password and so return an empty one) */
    fun getEditPassword(): GUIPassword {
        var password = GUIPassword()
        if (passwordToEdit != null) {
            password = GUIPassword.fromPasswordEntity(passwordToEdit as PasswordEntity)
            passwordToEdit = null
        }
        return password
    }

    var lastSearch: String = ""
    val passwordController = PasswordController()
    /* returns list of passwords matched by the last search, or all of them if a search was not performed */
    fun getPasswordSearchResults(): ObservableList<PasswordEntity> {
        var passwords: ObservableList<PasswordEntity>
        if (lastSearch.isNotEmpty()) {
            passwords = passwordController.getPasswordList(lastSearch)
            lastSearch = ""
            isSearch = true
        } else {
            passwords = passwordController.getPasswordList()
            isSearch = false
        }
        return passwords
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

    var isSearch: Boolean = false
    fun showViewPasswordsScreen() {
        if (FX.primaryStage.scene.root != viewPasswordsScreen.root) {
            FX.primaryStage.scene.root = viewPasswordsScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = if (isSearch) "Search Results" else "All Passwords"
    }

    fun showSearchScreen() {
        if (FX.primaryStage.scene.root != searchScreen.root) {
            FX.primaryStage.scene.root = searchScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = "Find Passwords"

        Platform.runLater {
            searchScreen.searchField.requestFocus()
        }
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

    fun doSearch(search: String) {
        runAsync {
            lastSearch = search
        } ui {
            searchScreen.clear()
            showViewPasswordsScreen()
        }
    }

    companion object {
        val PASSWORD = "password"
    }
}