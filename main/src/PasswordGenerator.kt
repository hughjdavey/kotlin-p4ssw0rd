import PasswordGenerator.Charset.*
import java.util.Random

object PasswordGenerator {
    enum class Charset(val chars: List<Char>) {
        ALPHA(listOf('a'..'z','A'..'Z').flatten()),
        NUMBER(listOf('1'..'9').flatten()),
        SYMBOL(listOf('!'..'/', ':'..'@', '['..'`', '{'..'~').flatten());
    }

    fun generatePassword(length: Int = 12, charsets: Array<Charset> = arrayOf(ALPHA, NUMBER)): Array<Char> {
        val random = Random()
        return (length downTo 1)
                .map { charsets[random.nextInt(charsets.size)].chars }
                .map { set -> set[random.nextInt(set.size)] }
                .toTypedArray()
    }
}

fun main(args: Array<String>) {
    println("hello")
    println( PasswordGenerator.Charset.ALPHA.chars.joinToString(", ") )
    println( PasswordGenerator.Charset.NUMBER.chars.joinToString(", ") )
    println( PasswordGenerator.Charset.SYMBOL.chars.joinToString(", ") )
    for (i in 1..10)
        println (PasswordGenerator.generatePassword(charsets = arrayOf(ALPHA, NUMBER, SYMBOL)).joinToString(""))
}

