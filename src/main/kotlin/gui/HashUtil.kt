package gui

import java.net.*
import java.nio.file.*
import java.security.*

/* utility object with methods to validate and change the user's password */
object HashUtil {
    fun passwordValid(password: String): Boolean {
        return readPasswordHash() == toHash(password);
    }

    fun setNewPassword(password: String): Boolean {
        return writePasswordHash(password)
    }

    private fun getHashUrl(): URL? {
        return Thread.currentThread().contextClassLoader.getResource("loginHash.txt");
    }

    private fun readPasswordHash(): String {
        val hashUrl = getHashUrl()
        return if (hashUrl != null) {
            String(Files.readAllBytes(Paths.get(hashUrl.path)))
        }
        else ""
    }

    private fun writePasswordHash(password: String): Boolean {
        val hashUrl = getHashUrl() ?: return false
        val writer = Files.newBufferedWriter(Paths.get(hashUrl.path))
        writer.write(toHash(password))
        writer.flush()
        writer.close()
        return true
    }

    private fun toHash(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256");
        digest.update(password.toByteArray());
        return String(digest.digest());
    }
}