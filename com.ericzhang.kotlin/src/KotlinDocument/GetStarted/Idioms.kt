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

    val customer: Customer = Customer("Eric", 100)
    customer.equals(Customer("", 1))

    test()

    println(transform("Blue"))
    println(transform("aaa"))
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
    println(files?.size ?: "empty")

    val s: String? = null
    println(s?.length)
    println(s ?: "empty")


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


