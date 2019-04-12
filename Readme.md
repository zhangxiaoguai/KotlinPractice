# Kotlin 语言文档

[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)
<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu"></a>

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

` 方法如果是带有大括号{}除了Unit类型外必须指定返回值类型，如果没有带有大括号而是直接使用表达式则可以通过自动类型判断。即：将表达式作为函数体。 `

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

### 编码规范

#### 命名风格

如果拿不准的时候，默认使用Java的编码规范，比如: 
— 使用驼峰法命名(并避免命名含有下划线)
— 类型名以大写字母开头
— 方法和属性以小写字母开头
— 使用 4 个空格缩进 
— 公有函数应撰写函数文档，这样这些文档才会出现在Kotlin Doc 中 

#### 冒号

类型和超类型之间的冒号前要有一个空格，而实例与类型之间的冒号前不要有空格：

```
interface Foo<out T : Any> : Bar {
	fun foo(a: Int): T 
}
```

#### Lambda 表达式

在lambda表达式中, 大括号左右要加空格，分隔参数与代码体的箭头左右也要加空格 。lambda表达应尽可能不要写在圆括号中。

```
list.filter { it > 10 }.map { element -> element * 2 }
```

在非嵌套的短lambda表达式中，最好使用约定俗成的默认参数 it 来替代显式声明参数名 。在嵌套的有参数的lambda表达式中，参数应该总是显式声。

#### 类头格式化

有少数几个参数的类可以写成一行: 

```
class Person(id: Int, name: String) 
```

具有较⻓类头的类应该格式化，以使每个主构造函数参数位于带有缩进的单独一行中。此外，右括号应该另起一行。如果我们使用继承，那么超类构造函数 
调用或者实现接口列表 应位于与括号相同的行上: 

```
class Person(
	id: Int,
	name: String,
	surname: String
) : Human(id, name) {
	// ...... 
}
```

对于多个接口，应首先放置超类构造函数调用，然后每个接口应位于不同的行中: 

```
class Person(
    id: Int,
    name: String,
    surname: String
) : Human(id, name),
    KotlinMaker { 
    // ...... 
} 
```

构造函数参数可以使用常规缩进或连续缩进(双倍的常规缩进)。 

#### Unit

如果函数返回 Unit 类型，该返回类型应该省略: 

```
fun foo() {// 省略了 ": Unit" 
	// do sth ...
} 
```

#### 函数还是属性

很多场合无参的函数可与只读属性互换。尽管语义相近，也有一些取舍的⻛格约定。 底层算法优先使用属性而不是函数: 
— 不会抛异常
— O(1) 复杂度
— 计算廉价(或缓存第一次运行)
— 不同调用返回相同结果 

## 基础

### 基本类型

在Kotlin中，所有东西都是对象，在这个意义上讲所以我们可以再任何变量上调用成员函数和属性。有些类型是内置的，因为他们的实现是优化过的。但是用户看起来他们就像普通的类。本节我们会描述大多数这些类型：数字，字符，布尔和数组。

#### 数字

Kotlin 处理数字在某种程度上接近 Java，但是并不完全相同。例如，对于数字没有隐式拓宽转换(如 Java 中 int 可以隐式转换为 long ——译者注)，另外有些情况的字面值略有不同。 

Kotlin 提供了如下的内置类型来表示数字（与 Java 很接近）：

Type | BitWidth
-----|-----
Double | 64
Float | 32
Long | 64
Int | 32
Short | 16
Byte | 8

注意： 在Kotlin 中字符不是数字

##### 字面常量

数值常量字面值有以下几种：

- 十进制： `123` ，Long 类型用大写 L 标记： 123L
- 十六进制： `0x0f`
- 二进制： `0b00001011`

注意： 不支持八进制

Kotlin 同样支持浮点数的常规表示方法：

- 默认 double ： 123.5 ， 123.5e10
- Float 用 f 或者 F 标记： 123.5F

##### 数字字面值中的下划线

你可以使用下划线使数字常量更易读：

```
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010 
```

##### 表示方式

在 Java 平台数字是物理存储为 JVM 的原生类型，除非我们需要一个可空的引用(如 Int? )或泛型。后者情况下会把数字装箱。 

注意数字装箱不必保留同一性：

```
val a: Int = 10000
val boxedA: Int? = a
val anotherBoxedA: Int? = a

// 同一性
println("a === 10000 : " + (a === 10000)) // true
println("a === a : " + (a === a)) // true
println("boxedA === anotherBoxedA : " + (boxedA === anotherBoxedA)) // false
```

另一方面，它保留了相等性：

```
val a: Int = 10000
val boxedA: Int? = a
val anotherBoxedA: Int? = a

// 相等性
println("a == 10000 : " + (a == 10000)) // true
println("a == a : " + (a == a)) // true
println("boxedA == anotherBoxedA : " + (boxedA == anotherBoxedA)) // true
```

##### 显示转换

由于不同的表示方式，较小类型并不是较大类型的子类型。如果他们是的话，就会出现下述问题：

```
// 假想的代码，实际上并不能编译:
val a: Int? = 1 // 一个装箱的 Int (java.lang.Integer)
val b: Long? = a // 隐式转换产生一个装箱的 Long (java.lang.Long)
print(a == b) // 惊!这将打印 "false" 鉴于 Long 的 equals() 检测其他部分也是 Long 
```

实际上：

```
val a: Int? = 1
val b: Long? = a // Type mismatch: inferred type is Int? but Long? was expected
println(a == b) // Operator '==' cannot be applied to 'Int?' and 'Long?'
```

和：

```
val a: Int = 1
val b: Long = a // Type mismatch: inferred type is Int but Long was expected
println(a == b) // Operator '==' cannot be applied to 'Int' and 'Long'
```

所有同一性还有相等性都会在这些所有地方悄无声息地失去。
因此较小的类型不能隐式转换为较大的类型。这意味着在不进行显示转换的情况下我们不能把 `Byte` 型值赋给一个 `Int` 变量。

```
val b: Byte = 1
val i: Int = b // Type mismatch: inferred type is Byte but Int was expected
```

我们可以显式转换来拓宽数字：

```
val b: Byte = 1
val i: Int = b.toInt() // OK: 显式拓宽
```

每个数字类型支持如下的转换: 

— toByte(): Byte
— toShort(): Short
— toInt(): Int
— toLong(): Long
— toFloat(): Float
— toDouble(): Double
— toChar(): Char 

```
'A'.toInt() // 65
'a'.toInt() // 97
```

缺乏隐式类型转换并不显著，因为类型会从上下文推断出来，而算术运算会有重载做适当转换，例如: 

```
val l = 1L + 3 // Long + Int => Long
```

##### 运算

Kotlin支持数字运算的标准集，运算被定义为相应的类成员(但编译器会将函数调用优化为相应的指令)。参⻅运算符重载。对于位运算，没有特殊字符来表示，而只可用中缀方式调用命名函数，例如: 

```
val x = (1 shl 2) and 0x000FF000
```

这是完整的位运算列表(只用于 Int 和 Long ): 

— shl(bits) ‒ 有符号左移 (Java 的 << ) 
— shr(bits) ‒ 有符号右移 (Java 的 >> ) 
— ushr(bits) ‒ 无符号右移 (Java 的 >>> ) 
— and(bits) ‒位与 
— or(bits) ‒位或
— xor(bits) ‒位异或 
— inv() ‒位非

#### 字符

字符用 Char 类型表示。它们不能直接当作数字 

```
fun check(c: Char) {
    if (c == 1){// Operator '==' cannot be applied to 'Char' and 'Int'
    }
}
```

字符字面值用单引号括起来: '1' 。特殊字符可以用反斜杠转义。支持这几个转义序列:\t 、\b 、\n 、\r 、\' 、\" 、\\ 和 \$ 。编码其他字符要用 Unicode 转义序列语法:'\uFF00' 。 

我们可以显式把字符转换为 `Int` 数字: 

```
fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9') {
        throw IllegalArgumentException("Out of range")
    }
    return c.toInt() - '0'.toInt()
}
```

当需要可空引用时，像数字、字符会被装箱。装箱操作不会保留同一性。 

#### 布尔

布尔用 Boolean 类型表示，它有两个值： true 和 false。 
若需要可空引用布尔会被装箱。 内置的布尔运算有: 

* || 短路逻辑或
* && 短路逻辑与
* ! 逻辑非 

#### 数组

数组在 Kotlin 中使用 `Array` 类来表示，它定义了 `get` 和 `set` 函数（按照运算符重载约定这回转变为 [] ）和 size 属性，以及一些其他有用的成员函数：

```
class Array<T> private constructor() {
	val size: Int 
	operator fun get(index: Int): T
	operator fun set(index: Int, value: T): Unit 
	operator fun iterator(): Iterator<T> 
	// ...... 
} 
```

我们可以使用库函数 arrayOf() 来创建一个数组并传递元素值给它，这样 arrayOf(1, 2, 3) 创建了 array [1, 2, 3]。或者，库函数 arrayOfNulls() 可以用于创建一个指定大小、元素都为空的数组。 

另一个选项是用接受数组大小和一个函数参数的工厂函数，用作参数的函数能够返回给定索引的每个元素初始值: 

```
// 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
val array = Array(5, { it -> (it * it).toString() })
```

如上所述，[] 运算符代表调用成员函数 get() 和 set() 。 
注意: 与 Java 不同的是，Kotlin 中数组是不型变的(invariant)。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any> ，以防止可能的运行时失败(但是你可以使用 Array<out Any> , 参⻅类型投影)。 
```
val arrayOf = arrayOf(1, 5, 9)
var arrayOf1 = arrayOf(1, "haha", 'b', 100L)
var arrayOf2: Array<out Any> = arrayOf(1, "haha", 'b', 100L)

arrayOf1 = arrayOf // Type mismatch
arrayOf2 = arrayOf // ok
```

Kotlin 也有无装箱开销的专⻔的类来表示原生类型数组: ByteArray 、ShortArray 、IntArray 等等。这些类和 Array 并没有继承关系，但是 它 
们有同样的方法属性集。它们也都有相应的工厂方法: 

```
val byteArrayOf = byteArrayOf('a'.toByte(), '6'.toByte(), 'd'.toByte())
val intArrayOf = intArrayOf(1, 5, 9, 10)
val shortArrayOf = shortArrayOf(1, 9, 1)

intArrayOf[2] = intArrayOf[0] + intArrayOf[1]
```

#### 字符串

字符串用 String 类型表示。字符串是不可变的。字符串的元素字符可以使用索引运算符访问: s[i] 。

可以用 for 循环迭代字符串: 

```
for (c in str) {
	println(c) 
} 
```

##### 字符串字面值

Kotlin 有两种类型的字符串字面值: 转义字符串可以有转义字符，以及原生字符串可以包含换行和任意文本。转义字符串很像 Java 字符串: 

```
val s = "Hello, world!\n" 
```

转义采用传统的反斜杠方式。参⻅上面的 字符 查看支持的转义序列。 

`原生字符串` 使用三个引号( """ )分界符括起来，内部没有转义并且可以包含换行和任何其他字符: 

```
val s = """fun
asdfa   asdf
  as
      fsdf
        """
```

可以通过 `trimMargin()` 函数去除前后空格：

```
val s = """fun
asdfa   asdf
  as
      fsdf
        """.trimMargin()
```

默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">") 。 

##### 字符串模板

字符串可以包含模板表达式，即一些小段代码，会求值并把结果合并到字符串中。模板表达式以美元符($)开头，由一个简单的名字构成：

```
val i = 10
val s = "i = $i"
```

或者用花括号括起来的任意表达式：

```
val s = "abcd"
// 求值结果为 "abc.length is 3" 
val str = "$s.length is ${s.length}"
```

原生字符串和转义字符串内部都支持模板。如果你需要在原生字符串中表示字面值 `$` 字符(它不支持反斜杠转义)，你可以用下列语法: 

```
val price = """ ${'$'}9.99 """ 
```

### 包

源文件通常以包声明开头：

```
package foo.bar
fun baz(){}
class Goo {}
```

源文件所有内容(无论是类还是函数)都包含在声明的包内。所以上例中 `baz()` 的全名是 `foo.bar.baz` 、`Goo` 的全名是 `foo.bar.Goo` 。 如果没有指明包，该文件的内容属于无名字的默认包。 

#### 默认导入

有多个包会默认导入到每个 Kotlin 文件中:
 
— kotlin.*
— kotlin.annotation.*
— kotlin.collections.*
— kotlin.comparisons.*(自 1.1 起)
— kotlin.io.*
— kotlin.ranges.*
— kotlin.sequences.*
— kotlin.text.* 

根据目标平台还会导入额外的包:

— JVM:
—- java.lang.* 
—- kotlin.jvm.*

— JS: 
—- kotlin.js.* 

#### 导入

除了默认导入之外，每个文件可以包含它自己的导入指令。导入语法在 `语法` 中讲述。 

可以导入一个单独的名字，如：

```
import foo.Bar // 现在 Bar 可以不用限定符访问
```

也可以导入一个作用域下的所有内容(包、类、对象等)：

```
import foo.* // “foo”中的一切都可访问 
```

如果出现名字冲突，可以使用 as 关键字在本地重命名冲突项来消歧义: 

```
import foo.Bar // Bar 可访问
import bar.Bar as bBar // bBar 代表“bar.Bar” 
```

关键字 `import` 并不仅限于导入类,也可用它来导入其他声明: 

— 顶层函数及属性
— 在对象声明中声明的函数和属性
— 枚举常量 

与 Java 不同，Kotlin 没有单独的 "import static" 语法，所有这些声明都用 import 关键字导入。 

#### 顶层声明的可⻅性 
如果顶层声明是 private 的，它是声明它的文件所私有的(参⻅ 可⻅性修饰符)。 


### 控制流

#### if 表达式

在 Kotlin 中，if是一个表达式，即他会返回一个值。因此就不需要三元运算符。因为普通的if就能胜任这个角色。

```
// With else 
val max: Int
if (a > b) {
    max = a
} else {
    max = b
}

// 作为表达式
val max2 = if (a > b) a else b
```

if的分支可以是代码块，最后的表达式作为该块的值: 

```
val max3 = if (a > b) {
    print("Choose a")
    a
} else {
    print("Choose b")
    b
}
```

如果你使用if作为表达式而不是语句(例如:返回它的值或者把它赋给变量)，该表达式需要有 else 分支。 

#### when 表达式

`when` 取代了类 C 语言的 switch 操作符。其最简单的形式如下: 

```
when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> // 注意这个块
        print("x is neither 1 nor 2")
}
```

when 将它的参数和所有的分支条件顺序比较，知道某个分支满足条件。 when 既可以被当做表达式使用，也可以被当做语句使用。如果被当做表达式，符合条件的分支的值就是整个表达式的值，如果当做语句使用，则忽略个别分支的值。像 if 一样，每一个分支可以是一个代码块，它的值 是块中最后的表 达式的值。) 

`如果其他分支都不满足条件将会求值 else 分支。如果 when 作为一个表达式使用，则必须有 else 分支，除非编译器能够检测出所有的可能情况都已经覆盖了。 `

如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔: 

```
when (x) {
    3, 4, 5 -> println(x)
    else -> println("else")
}
```

我们可以用任意表达式(而不只是常量)作为分支条件：

```
when (x) {
	parseInt(s) -> print("s encodes x")
	else -> print("s does not encode x")
}
```

我们也可以检测一个值在(in)或者不在(!in)一个区间或者集合中: 

```
when (x) {
	in 1..10 -> print("x is in the range")
	in validNumbers -> print("x is valid")
	!in 10..20 -> print("x is outside the range")
	else -> print("none of the above")
}
```

另一种可能性是检测一个值是(is)或者不是(!is)一个特定类型的值。
注意:由于智能转换，你可以访问该类型的方法和属性而无需任何额外的检测：

```
fun hasPrefix(x: Any) = when(x) {
	is String -> x.startsWith("prefix")
	else -> false
}
```

when 也可以用来取代 if-else if链。如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支: 

```
when {
	x.isOdd() -> print("x is odd")
	x.isEven() -> print("x is even")
	else -> print("x is funny")
}
```

#### for 循环

for 循环可以对任何提供迭代器(iterator)的对象进行遍历，语法如下: 

```
for (item in collection)
	print(item) 
```

循环体可以是一个代码块:

```
for (item: Int in ints) { 
	// do sth ......
}
```

如上所述，for 可以循环遍历任何提供了迭代器的对象。即: 

— 有一个成员函数或者扩展函数 `iterator()` ，它的返回类型
—— 有一个成员函数或者扩展函数 `next()` ，并且
—— 有一个成员函数或者扩展函数 `hasNext()` 返回 Boolean 。 
这三个函数都需要标记为 `operator` 。 
对数组的 `for` 循环会被编译为并不创建迭代器的基于索引的循环。 

如果你想要通过索引遍历一个数组或者一个 list，你可以这么做: 

```
for (i in array.indices) {
	print(array[i])
}
```

注意这种“在区间上遍历”会编译成优化的实现而不会创建额外对象。 

或者你可以用库函数 `withIndex` : 

```
for ((index, value) in array.withIndex()) {
	println("the element at $index is $value")
}
```

#### while 循环

while 和 do..while 照常使用:

```
while (x > 0) {
	x--
}

do {
	val y = retrieveData()
} while (y != null) // y 在此处可⻅
```

#### 循环中的 break 和 continue

在循环中 Kotlin 支持传统的 break 和 continue 操作符。参⻅返回和跳转。 

### 返回和跳转

Kotlin 有三种结构化跳转表达式: 

- return:默认从最直接包围它的函数或者匿名函数返回
- break:终止最直接包围它的循环
- continue:继续下一次最直接包围它的循环

所有这些表达式都可以用作更大表达式的一部分: 

```
val s = person.name ?: return
```

这些表达式的类型是 `Nothing` 类型。 

#### Break 和 Continue 标签 
在 Kotlin 中任何表达式都可以用标签(label)来标记。标签的格式为标识符后跟 @ 符号，例如:abc@、fooBar@ 都是有效的标签(参⻅语法)。要为一个表达式加标签，我们只要在其前加标签即可。 

```
loop@ for (i in 1..100) {
	// ......
}
```

现在，我们可以用标签限制 break 或者continue: 

```
loop@ for (i in 1..100) {
	for (j in 1..100) {
		if (......) 
			break@loop
	}
}
```

标签限制的 break 跳转到刚好位于该标签指定的循环后面的执行点。continue 继续标签指定的循环的下一次迭代。 

#### 标签处返回 

Kotlin 有函数字面量、局部函数和对象表达式。因此 Kotlin 的函数可以被嵌套。标签限制的 return 允许我们从外层函数返回。
最重要的一个用途就是从 lambda 表达式中返回。回想一下我们这么写的时候: 

```
fun foo1() {
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach {
        if (it == 6) return // 此处返回最外层foo1()方法
        println(it)
    }
}
```

这个return表达式从最直接包围它的函数即 foo 中返回。(注意，这种非局部的返回只支持传给内联函数的lambda表达式。)

如果我们需要从 lambda 表达式中返回，我们必须给它加标签并用以限制 return，如下：

 ```
 fun foo2() {
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach lit@ {
        if (it == 6) return@lit
        println(it)
    }
}
```

现在，它只会从 lambda 表达式中返回。通常情况下使用隐式标签更方便。该标签与接受该 lambda 的函数同名。 

```
fun foo3() {
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach {
        if (it == 6) return@forEach
        println(it)
    }
}
```

或者，我们用一个 `匿名函数` 替代 lambda 表达式。匿名函数内部的 return 语句将从该匿名函数自身返回 

```
fun foo4() {
    println("foo4---------")
    val intArrayOf = intArrayOf(2, 4, 6, 8)
    intArrayOf.forEach(fun(value: Int) {
        if (value == 4) return
        println(value)
    })
}
```

当要返一个回值的时候，解析器优先选用标签限制的 return，即:

```
return@a 1
```

意为“从标签 @a 返回 1”，而不是“返回一个标签标注的表达式 (@a 1) ”。 





[toc]


