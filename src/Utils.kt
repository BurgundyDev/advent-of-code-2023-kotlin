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
@Suppress("Unused")
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
@Suppress("UNCHECKED_CAST")
fun <T : Number> gcd(a: T, b: T): T {
    val aLong = a.toLong()
    val bLong = b.toLong()
    val result = if (bLong == 0L) aLong else gcd(bLong, aLong % bLong)
    return when (a) {
        is Byte -> result.toByte()
        is Short -> result.toShort()
        is Int -> result.toInt()
        is Long -> result
        is Float -> result.toFloat()
        is Double -> result.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type")
    } as T
}
@Suppress("UNCHECKED_CAST")
fun <T : Number> lcm(a: T, b: T): T {
    val aLong = a.toLong()
    val bLong = b.toLong()
    val result = (aLong * bLong) / gcd(aLong, bLong)
    return when (a) {
        is Byte -> result.toByte()
        is Short -> result.toShort()
        is Int -> result.toInt()
        is Long -> result
        is Float -> result.toFloat()
        is Double -> result.toDouble()
        else -> throw IllegalArgumentException("Unsupported number type")
    } as T
}