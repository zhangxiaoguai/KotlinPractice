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

### 习惯用法

#### 创建DTOs（POJOs/POCOs）

Data Transfer Object，数据传输对象。
Plain Ordinary Java Object，简单的Java对象，一般指普通JavaBean。
Plain Ordinary C# Object。

```
data class Customer(val name: String, val age: Int)
```

会为 `Customer` 类提供以下功能：
-- 所有属性的 `getter` （对于 `var` 定义的属性还有 `setter` ）
-- `equals()`
-- `hashCode()`
-- `toString()` 
-- `copy()`
-- 所有属性的 `component1()` , `component2()` ……等等。

#### 函数的默认参数

```
fun foo(val a: Int = 9, val s: String = "haha"){
	//do sth... 
}
```

#### 过滤 list 

```
val positives = list.filter { x -> x > 0 } 
```

或者更短：

```
// 默认参数为it
val positives = list.filter { it > 0 } 
```

#### String 内插

```
println("Name: $name")
```

#### 类型判断

```
when (x){
	is Foo -> // do sth ...
	is Bar -> // do sth ...
	else -> // do sth ...
}
```

#### 遍历 map/pair 型 list

```
for((k, v) in map){
	println("$k -> $v")
}
```

`k` 、`v` 可以改成任意名字。 

#### 使用区间

```
for (i in 1..10) {...} // 闭区间：包含10
for (i in 1 until 10) { ...... } // 半开区间:不包含10
for (x in 2..10 step 2) { ...... }
for (x in 10 downTo 1) { ...... }
if (x in 1..10) { ...... } 
```

#### 只读 list

```
val list = listOf("a", "b", "c")
```

#### 只读 map

```
// key 是 a，value 是 1。
val map = mapOf("a" to 1, "b" to 2, "c" to 3)
```

#### 访问 map

```
println(map["key"]) 
map["key"] = value 
```

#### 延迟属性

```
val p: String by lazy {
    // do sth ...
    "haha"
}
```

#### 拓展函数

```
fun String.spaceToCamelCase() { ...... }
"Convert this to camelcase".spaceToCamelCase()
```

#### 创建单例

```
object Resource {
	val name = "Name" 
} 
```

#### if not null 缩写

```
val files = File("Test").listFiles()
println(files?.size)
```

#### if not null and else 缩写

```
val files = File("Test").listFiles()
println(files?.size ?: "empty") 
```

#### if null 执行代码

```
val data = ...
val email = data["email"] ?: throw IllegalStateException("Email is missing!") 
```

#### if not null 执行代码

```
val data = ...... 
data?.let {
	...... // 代码会执行到此处, 假如data不为null 
} 
```

#### 返回 when 表达式

```
fun transform(color: String): Int {
	return when(color){
		"Red" -> 0
		"Green" -> 1
		"Blue" -> 2
		else -> throw IllegalArgumentException("invalid color param value")
	}
}
```

#### try/catch 表达式

```
fun testTryCatch() {
    val result = try {
        foo()
    } catch (e: ArithmeticException) {
        throw IllegalArgumentException(e)
    }

    // 使用result
}
```

#### if 表达式

```
fun testIf(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else if (param == 3) {
        "three"
    } else {
        "none"
    }
    // do sth ...
}
```

#### 返回类型为 Unit 的方法的 Builder 风格用法

```
// -1的集合
fun arrayOfMinusOnes(size: Int): IntArray {
    return IntArray(size).apply { fill(-1) }
}
```

#### 单表达式函数

```
fun theAnswer() = 42 
```

等价于：

```
fun theAnswer(): Int {
	return 42 
} 
```

单表达式函数与其他惯用法一起使用能简化代码，例如和 when 表达式一起使用：

```
fun transform(color: String): Int = when (color){
	"Red" -> 0
	"Blue" -> 1
	"Green" -> 2
	else -> -1
}
```

#### 对一个对象实例调用多个方法（with）

```
class Turtle { 
	fun penDown() 
	fun penUp()
	fun turn(degrees: Double) 
	fun forward(pixels: Double) 
} 

val myTurtle = Turtle()
with(myTurtle) { // 画一个 100 像素的正方形 
	penDown()
	for(i in 1..4) { 
		forward(100.0)
		turn(90.0) 
	} 
	penUp() 
} 
```

#### Java 7 的 try with resources

```
val stream = Files.newInputStream(Paths.get("/some/file.txt")) 
stream.buffered().reader().use { 
	reader -> println(reader.readText()) 
} 
```

#### 对于需要泛型信息的泛型函数的适宜形式

```
// public final class Gson {
// ...... 
// public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
// ...... 
inline fun <reified T: Any> Gson.fromJson(json): T = this.fromJson(json, T::class.java) 
```

#### 使用可空布尔

```
val b: Boolean? = // do sth ...
if (b == true) { 
    // do sth ...
} else {
    // `b` 是 false 或者 null 
    // do sth ...
} 
```

















[toc]
