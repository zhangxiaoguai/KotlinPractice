/**
 * @author EricZhang
 * @email ericzhangisworking@gmail.com
 * @date 2017/11/26 上午11:10
 */
package KotlinDocument.GetStarted


fun main(args: Array<String>) {
    printSum1(1, 2)
    printSum2(2, 3)

    testStringTemplate()

    printProduct("haha", "hehe")
    printProduct2("haha", "hehe")

    testFor()
    testFor2()

    testWhile()

    println(description("Hello"))

    testIn()
    testIn2()
    testIn3()
    testIn4()

    testCollectionFilter()
}

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun printSum1(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}

fun localValDef() {
    val a: Int = 1
    val b = 2
    val c: Int
    c = 3

    var x = 5
    x += 1
}

fun testStringTemplate() {
    var a = 1
    // 模板中的简单名称
    val s1 = "a is $a"

    a = 2
    // 模板中的任意表达式
    val s2 = "${s1.replace("is", "was")},but now is $a"

    println(s1)
    println(s2)
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun maxOf2(a: Int, b: Int) = if (a > b) a else b

fun parseInt(str: String): Int? {
    return 2
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // 直接使用 'x * y' 可能会报错，因为它们可能为null
    if (x != null && y != null) {
        // 在空检测后，x和y会自动转换为非空值
        println(x * y)
    } else {
        println("either '$arg1' or '$arg2' is not a number")
    }
}

fun printProduct2(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    if (x == null) {
        println("Wrong number format in arg1: '$arg1'")
        return
    }

    if (y == null) {
        println("Wrong number format in arg1: '$arg2'")
        return
    }

    // 在空检测后，x 和 y 会自动转换为非空值
    println(x * y)
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // obj 在该条件分支内自动转换成 String
        return obj.length
    }

    // 在离开类型检测分支后，obj 仍然是 Any 类型
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) {
        return null
    }

    // obj 在这一分支自动转换成 String
    return obj.length
}

fun getStringLength3(obj: Any): Int? {
    // obj 在 && 右边自动转换成 String 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}

fun testFor() {
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println("$item length is ${item.length}")
    }
}

fun testFor2() {
    val items = listOf("apple", "banana", "kiwi")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

fun testWhile() {
    val items = listOf("apple", "banana", "kiwi")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun description(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknow"
        }

fun testIn() {
    val x = 10
    val y = 9
    // 包括1和y+1
    if (x in 1..y + 1) {
        println("fits in range")
    }
}

fun testIn2() {
    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {// true
        println("-1 is out of range")
    }
    if (list.size !in list.indices) {// true
        println("list size is out of valid list indices range too")
    }
}

fun testIn3() {
    for (x in 1..5) {
        println(x)
    }
}

fun testIn4() {
    for (x in 1..10 step 2) {
        println(x)
    }
    for (x in 10 downTo 0 step 3) {
        println(x)
    }
}

fun testCollectionFilter() {
    val list = listOf("banana", "apple", "app", "bigger", "zipper", "agent", "ericzhang")
    list
            .filter { it.startsWith("a") }
            .sortedBy { it }// 按字母顺序排序
            .map { it.toUpperCase() }
            .forEach { println(it) }

    list
            .filter { it.startsWith("a") }
            .sortedBy { it[2] }// 按第三个字母排序
            .map { it.toUpperCase() }
            .forEach { println(it) }
}
