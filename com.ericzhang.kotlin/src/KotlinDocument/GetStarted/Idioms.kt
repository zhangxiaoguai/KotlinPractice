package KotlinDocument.GetStarted

import java.io.File


/**
 * @author EricZhang
 * @email ericzhangisworking@gmail.com
 * @date 2017/11/27 下午8:32
 */
// DTO
data class Customer(val name: String, val age: Int)

fun main(args: Array<String>) {
    println("------------")

    val customer: Customer = Customer("Eric", 100)
    customer.equals(Customer("", 1))

    test()

    println(transform("Blue"))
    try {
        println(transform("aaa"))
    } catch (e: Exception) {
        e.printStackTrace()
    }

    println(testIf(2))

    val arrayOfMinusOnes = arrayOfMinusOnes(6)

    testWith()

    println("------------")
}

fun foo(a: Int = 9, s: String = "haha") {}

fun test() {
    val listOf = listOf(1, 2, 3, 4)
    listOf.filter { x -> x > 0 }
    listOf.filter { it > 0 }

    val list = listOf("a", "b", "c")
    // key 是 a，value 是 1。
    val map = mapOf("a" to 1, "b" to 2, "c" to 3)

    println(map["a"])

    val mutableMapOf = mutableMapOf("a" to 1, "b" to 2, "c" to 3)
    mutableMapOf["a"] = 100

    foo()

    val p: String by lazy {
        // do sth ...
        "haha"
    }

    val files = File("Test").listFiles()
    println(files?.size ?: "file is empty")

    val s: String? = null
    println(s?.length)
    println(s ?: "s is empty")
}

object Singleton {
    val name = "Singleton"
}

fun transform(color: String): Int {
    return when (color) {
        "Red" -> 0
        "Green" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("invalid color param value")
    }
}

fun testTryCatch() {
    val result = try {
        foo()
    } catch (e: ArithmeticException) {
        throw IllegalArgumentException(e)
    }

    // 使用result
}

fun testIf(param: Int): String {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else if (param == 3) {
        "three"
    } else {
        "none"
    }
    return result + "  aaa"
}

/**
 * -1的集合
 */
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}

fun transform2(color: String): Int = when (color) {
    "Red" -> 0
    "Blue" -> 1
    "Green" -> 2
    else -> -1
}

class Turtle {
    fun penDown() {
        println("Turtle penDown")
    }

    fun penUp() {
        println("Turtle penUp")
    }

    fun turn(degrees: Double) {
        println("Turtle turn：" + degrees)
    }

    fun forward(pixels: Double) {
        println("Turtle forward：" + pixels)
    }
}

fun testWith() {
    val turtle = Turtle()
    with(turtle) {
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}






