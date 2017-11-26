# Kotlin 语言文档

## 开始

### 基本语法

#### 定义包

包的声明应为于源文件的顶部：

```
package my.demo
import java.util.*
```

目录与包的结构无需匹配：源代码可以在文件系统的任意位置。

#### 定义函数

带有两个 `Int` 参数，返回 `Int` 的函数：

```
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

将表达式作为函数体，返回值类型自动推断的函数：

`方法如果是带有大括号{}除了Unit类型外必须指定返回值类型，如果没有带有大括号而是直接使用表达式则可以通过自动类型判断。即：将表达式作为函数体。`

```
fun sum2(a: Int, b: Int) = a + b
```

函数返回无意义的值：

```
fun printSum1(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}
```

`Unit` 返回类型可以省略：

```
fun printSum2(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}
```

#### 定义局部变量

一次赋值（只读）的局部变量：

```
val a: Int = 1 // 立即赋值
val b = 2 // 自动推断出 `Int` 类型
val c: Int // 如果没有初始值类型不能省略
c = 3 // 明确赋值
```

可变变量：

```
var x = 5 // 自动推断出 `Int` 类型
x += 1
```

#### 注释

正如 Java 和 JavaScript，Kotlin 支持行注释及块注释。

```
// 这是一个行注释

/*
 * 这是一个多行的块注释
 */
```

#### 使用字符串模板

```
var a = 1
// 模板中的简单名称
val s1 = "a is $a"

a = 2
// 模板中的任意表达式
val s2 = "${s1.replace("is","was")},but now is $a"
```

#### 使用条件表达式

```
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}
```

使用if作为表达式：

```
fun maxOf(a: Int, b: Int) = if (a > b) a else b
```

#### 使用可空值及 null 检测

当某个变量的值可以为 `null` 的时候，必须在声明处的类型后添加 `?` 来标识该引用可为空。

如果 `str` 的内容不是数字返回 `null`：

```
fun parseInt(str: String): Int? {
    // ...
}
```

使用返回可空值的函数：

```
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

```

或者

```
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
```

#### 使用类型检测及自动类型转换

`is` 运算符检测一个表达式是否是某类型的一个实例。如果一个不可变的局部变量或属性已经判断为某类型，那么检测后的分支当中可以直接当作该类型使用，无需显示转换：

```
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // obj 在该条件分支内自动转换成 String
        return obj.length
    }

    // 在离开类型检测分支后，obj 仍然是 Any 类型
    return null
}
```

或者：

```
fun getStringLength2(obj: Any): Int? {
    if (obj !is String) {
        return null
    }

    // obj 在这一分支自动转换成 String
    return obj.length
}
```

甚至：

```
fun getStringLength3(obj: Any): Int? {
    // obj 在 && 右边自动转换成 String 类型
    if (obj is String && obj.length > 0) {
        return obj.length
    }

    return null
}
```

#### 使用 for 循环

```
val items = listOf("apple", "banana", "kiwi")
for (item in items) {
    println("$item length is ${item.length}")
}
```

或者：

```
val items = listOf("apple", "banana", "kiwi")
for (index in items.indices) {
    println("item at $index is ${items[index]}")
}
```

#### 使用 while 循环

```
val items = listOf("apple", "banana", "kiwi")
var index = 0
while (index < items.size) {
    println("item at $index is ${items[index]}")
    index++
}
```

#### 使用 when 表达式

```
fun description(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknow"
        }
```

#### 使用区间（range）

使用 `in` 运算符来检测某个数字是否在指定区间内：

```
val x = 10
val y = 9
// 包括1和y+1
if (x in 1..y + 1) {
    println("fits in range")
}
```

检测某个数字是否在指定区间外：

```
val list = listOf("a", "b", "c")
// true
if (-1 !in 0..list.lastIndex) {
    println("-1 is out of range")
}
// true
if (list.size !in list.indices) {
    println("list size is out of valid list indices range too")
}
```

区间迭代：

```
for (x in 1..5) {
    println(x)
}
// 类似于java中的for循环
// for(int x = 1;x <= 5;x++)
```

或数列迭代：

```
for (x in 1..10 step 2) {
    println(x)
}
// for(int x = 1;x <= 10;x += 2)
for (x in 10 downTo 0 step 3) {
    println(x)
}
// for(int x = 10;x >= 0;x -= 3)
```

#### 使用集合

对集合进行迭代：

```
for(item in items) {
	println(item)
}
```

使用 `in` 运算符来判断集合内是否包含某实例：

```
when {
	"orange" in items -> println("juicy")
	"apple" in items -> println("apple is fine too")
}
```

使用 `lambda` 表达式来过滤和映射集合：

```
val list = listOf("banana", "apple", "app", "bigger", "zipper", "agent")
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
```

#### Creating basic classes and their instances

```
val rectangle = Rectangle(5.0, 2.0) //no 'new' keyword required
val triangle = Triangle(3.0, 4.0, 5.0)
```


[toc]


