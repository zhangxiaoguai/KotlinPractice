/**
 * @author EricZhang
 * @email ericzhangisworking@gmail.com
 * @date 2017/11/28 下午1:50
 */
package KotlinDocument.Basics

val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010

fun main(args: Array<String>) {
    testBox()

    /*
    testTransform()
    testTransform2()
    */

    testTransform3()
    testOperation()

    println(decimalDigitValue('8'))
    println('A'.toInt()) // 65
    println('a'.toInt()) // 97

    testArray()
    testBasicArray()

    testString()

    testStringTemplate()

}

fun testBox() {
    val a: Int = 10000
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    // 同一性
    println("a === 10000 : " + (a === 10000)) // true
    println("a === a : " + (a === a)) // true
    println("a === boxedA : " + (a === boxedA)) // true
    println("boxedA === anotherBoxedA : " + (boxedA === anotherBoxedA)) // false

    // 相等性
    println("a == 10000 : " + (a == 10000)) // true
    println("a == a : " + (a == a)) // true
    println("a == boxedA : " + (a == boxedA)) // true
    println("boxedA == anotherBoxedA : " + (boxedA == anotherBoxedA)) // true

    val b: Int = 8 // -128 ~ 127 不会装箱
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    // 同一性
    println("b === 8 : " + (b === 8)) // true
    println("b === b : " + (b === b)) // true
    println("b === boxedB : " + (b === boxedB)) // true
    println("boxedB === anotherBoxedB : " + (boxedB === anotherBoxedB)) // false

    // 相等性
    println("b == 8 : " + (b == 8)) // true
    println("b == b : " + (b == b)) // true
    println("b == boxedB : " + (b == boxedB)) // true
    println("boxedB == anotherBoxedB : " + (boxedB == anotherBoxedB)) // false
}

/*
fun testTransform() {
    val a: Int? = 1
    val b: Long? = a // Type mismatch: inferred type is Int? but Long? was expected
    println(a == b) // Operator '==' cannot be applied to 'Int?' and 'Long?'

    val c: Int = 1
    val d: Long = c // Type mismatch: inferred type is Int but Long was expected
    println(c == d) // Operator '==' cannot be applied to 'Int?' and 'Long?'
}

fun testTransform2() {
    val b: Byte = 1
    val i: Int = b // Type mismatch: inferred type is Byte but Int was expected

    println("b:" + b)
    println("i" + i)
}
*/

fun testTransform3() {
    val b: Byte = 1
    val i: Int = b.toInt()

    println("b : " + b)
    println("i : " + i)

    val l = 1L + 3 // Long + Int => Long
}

fun testOperation() {
    val x = (1 shl 2) and 0x000FF000
    println("x : " + x)

    val i = 1 and 2
    val i1 = 1 or 2
    val i2 = (1 xor 2)
}

fun check(c: Char) {
//    if (c == 1){// Operator '==' cannot be applied to 'Char' and 'Int'
//    }
}


fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9') {
        throw IllegalArgumentException("Out of range")
    }
    return c.toInt() - '0'.toInt()
}

fun testArray() {
    val arrayOf = arrayOf(1, 5, 9)
    val arrayOfNulls = arrayOfNulls<Int>(3)

    // 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    val array = Array(5, { it -> (it * it).toString() })

    var arrayOf1 = arrayOf(1, "haha", 'b', 100L)
    var arrayOf2: Array<out Any> = arrayOf(1, "haha", 'b', 100L)

//    arrayOf1 = arrayOf // Type mismatch
//    arrayOf2 = arrayOf // ok

    println("---------")
}

fun testBasicArray() {
    val byteArrayOf = byteArrayOf('a'.toByte(), '6'.toByte(), 'd'.toByte())
    val intArrayOf = intArrayOf(1, 5, 9, 10)
    val shortArrayOf = shortArrayOf(1, 9, 1)

    intArrayOf[2] = intArrayOf[0] + intArrayOf[1]

    println("---------")
}

fun testString() {
    val s = """
        fun
asdfa   asdf
  as
      fsdf
        """

    println(s)

    val se = """
fun
asdfa   asdf
  as
      fsdf
        """.trimMargin()

    println(se)

    val s1 = """
        |Tell me and I forget
        |Teach me and I remember
        |Involve me and I learn
        |(Benjamin Franklin)
    """.trimMargin()

    println(s1)

    val s2 = """
        >Tell me and I forget
        >Teach me and I remember
        >Involve me and I learn
        >(Benjamin Franklin)
    """.trimMargin(">")

    println(s2)
}

fun testStringTemplate() {
    val s = "abc"
    val str = "$s.length is ${s.length}"
    println(str)

    val a = """$"""
    println(a)

    val aa = "${'$'}"
    println(aa)

    val aaa = "$"
    println(aaa)

    val price = """${'$'}9.99 """
    println(price)
}






