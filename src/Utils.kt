import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

class CircularIterator<T>(private val list: List<T>) : Iterator<T> {
    private var currentIndex = -1

    override fun hasNext(): Boolean {
        return list.isNotEmpty()
    }

    override fun next(): T {
        currentIndex = (currentIndex + 1) % list.size
        return list[currentIndex]
    }
}

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = (a * b) / gcd(a, b)
fun lcmOfList(numbers: List<Long>): Long = numbers.reduce(::lcm)