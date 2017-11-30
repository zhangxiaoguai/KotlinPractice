/**
 * @author EricZhang
 * @email ericzhangisworking@gmail.com
 * @date 2017/11/28 下午1:50
 */
package KotlinDocument.Basics

fun main(args: Array<String>) {

    testWhen(3)

    foo1()
    foo2()
    foo3()
    foo4()

}

//if
fun testIf() {
    val a = 10
    val b = 11

    val max: Int
    if (a > b) {
        max = a
    } else {
        max = b
    }

    val max2 = if (a > b) a else b

    val max3 = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }

}

fun testWhen(x: Int) {
    when (x) {
        1 -> println("x == 1")
        2 -> println("x == 2")
        else -> // 注意这个块
            println("x is neither 1 nor 2")
    }

    when (x) {
        3, 4, 5 -> println(x)
        else -> println("else")
    }

    loop@ for (i in 1..100) {

    }
}

fun foo1() {
    println("foo1---------")
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach {
        if (it == 4) return
        println(it)
    }
}

fun foo2() {
    println("foo2---------")
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach lit@ {
        if (it == 4) return@lit
        println(it)
    }
}

fun foo3() {
    println("foo3---------")
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach {
        if (it == 4) return@forEach
        println(it)
    }
}

fun foo4() {
    println("foo4---------")
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach(fun(value: Int) {
        if (value == 4) return
        println(value)
    })
}




