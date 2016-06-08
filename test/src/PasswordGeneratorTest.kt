import org.junit.Test as test
import org.junit.Assert.*
import org.hamcrest.CoreMatchers.*
import PasswordGenerator.Charset.*

class PasswordGeneratorTest {
    @test fun testGenerationReturnsPasswordOfSpecifiedSize() {
        val password = PasswordGenerator.generatePassword(length = 16);
        assertThat(password.size, equalTo(16))
    }

    @test fun testGenerationReturnsPasswordOfDefaultSize() {
        val password = PasswordGenerator.generatePassword();
        assertThat(password.size, equalTo(12))
    }

    @test fun testGenerationReturnsPasswordOfSpecifiedCharsets() {
        val password = PasswordGenerator.generatePassword(charsets = arrayOf(ALPHA, NUMBER, SYMBOL));
        assertTrue(containsCharsets(password, arrayOf(ALPHA, NUMBER, SYMBOL)))
    }

    @test fun testGenerationReturnsPasswordOfDefaultCharsets() {
        val password = PasswordGenerator.generatePassword();
        assertTrue(containsCharsets(password, arrayOf(ALPHA, NUMBER)))
    }

    fun containsCharsets(array: Array<Char>, sets: Array<PasswordGenerator.Charset>): Boolean {
        val res = sets.map { set ->
            array.filter { char -> set.chars.contains(char) }
        }
        return !res.contains(emptyList());
    }
}