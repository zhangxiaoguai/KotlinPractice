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

带有两个Int，返回Int的函数：

```
fun sum(a:Int,b:Int):Int{
    return a+b
}
```

讲表达式作为函数体，返回值类型自动推断的函数：

```
fun sum(a:Int,b:Int) = a+b
```











[TOC]

