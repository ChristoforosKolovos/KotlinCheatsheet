fun main(args: Array<String>) {
    testCollections()
}

//----------Variables----------
val a: Int = 1 //Immutable (can't be changed)
var b: Int = 2 //Mutable (can be changed)
//The best practice is to always use "val" until there is need for "val"

//constant variables (static val)
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

//----------Functions----------
//Block body
fun addNumbers1(a: Int, b: Int): Int {
    return a + b
}

//Expression Body
fun addNumbers2(a: Int, b: Int): Int = a + b

//Expression Body - Omitting return type (works because of type inference)
fun addNumbers(a: Int, b: Int) = a + b


//----------String formatting----------
fun stringFormatting() {
//Values
    println("The value of \"a\" is: $a")
//Expressions
    println("The sum of \"a\" and \"b\" is: ${a + b}")
}


//----------Ranges----------
val oneToTen = 1..10
val aToZ = "a".."z"

//----------Loops----------
//For loops
fun forLoops() {

    //for (item in collection)

    //Forward
    for (i in 1..100) {
        println(i);
    }

    //Backward
    for (i in 100 downTo 1) {
        println(i)
    }

    //With Step
    for (i in 1..100 step 5) {
        println(i)
    }
}

//While loops
fun whileLoops() {
    var a = 10

    //while
    while (a > 0) {
        a--
    }

    //do while
    do {
        val y = a;
    } while (y != null) // y is visible here!
}

//----------'in'----------
//Check if a value is in a range using 'in'
fun inTest() {
    if (a in 1..100) {
        //...
    }
}

//----------'is' and 'as'----------
//Type Checks and Casts: 'is' and 'as'
fun checkTest() {
    if (a is Int) {
        //...
    }

    if (a !is Int) {
        //...
    }

    val x = "test"
    if (x is String) {
        print(x.length) // x is automatically cast to String
    }
}

//----------When----------
fun testWhen() {
//    when (x) {
//        1 -> print("x == 1")
//        2 -> print("x == 2")
//        else -> {
//            print("x is neither 1 nor 2")
//        }
//    }
//
//    when (x) {
//        is Int -> print(x + 1)
//        is String -> print(x.length + 1)
//        is IntArray -> print(x.sum())
//    }
//
//    when (x) {
//        0, 1 -> print("x == 0 or x == 1")
//        else -> print("otherwise")
//    }
//
//    when (x) {
//        in 1..10 -> print("x is in the range")
//        in validNumbersCollection -> print("x is valid")
//        !in 10..20 -> print("x is outside the range")
//        else -> print("none of the above")
//    }
//
//    when {
//        x.isOdd() -> print("x is odd")
//        x.isEven() -> print("x is even")
//        else -> print("x is funny")
//    }
}


//----------Classes----------
//the best practice
class Person(val name: String) {
}
//val property is read only
//var property is writable

fun classTest() {
    val person = Person("Bob")
    val name = person.name //access property directly
}

//class property's getter and setter
class Book() {
    var title: String
        get() = this.title
        set(value) {
            this.title = value
        }
}
//we can add 'private' before set() and then the property won't be changed outside of the class

//backing filed from getter or setter
class Test() {
    var id: String = "" //in order to use 'field' there must be a default value, so the value must be initilized
        get() = this.id
        set(value) {
            println("value changed from $field to $value")
            field = value
        }
}

//----------Enums----------
enum class Color {
    RED,
    BLUE,
    GREEN
}

enum class Role(val id: Int) {
    GUEST(1234),
    USER(4567),
    ADMIN(6789);

    fun getRoleId() = id
}

//----------Infix functions----------
infix fun Any.to(other: Any) = Pair(this, other)

fun testInfix() {
    val (number, name) = 1 to "one" //Destructuring declaration
    println(number)
    println(name)
}

//----------Extensions----------

//Methods
fun String.lastChar() = this.get(this.length - 1)

fun testExtensionMethod() {
    println("test".lastChar())
}

//Property
var String.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        //...
    }


//----------Common Collections----------
fun testCollections() {

    val set1 = setOf(1, 4, 9, 3)
    val set2 = setOf(4, 1, 3, 9) //this equal with set1
    val hashSet = hashSetOf(1, 4, 9, 3) //unique items
    val list = listOf(1, 2, 3, 4, 5)
    val arrayList = arrayListOf(1, 4, 9, 3)
    val map1 = hashMapOf(Pair(1, 2), Pair(2, 3))
    val map2 = hashMapOf(1 to "one", 2 to "2") //with infix fun

    //Join to String
    println(list.joinToString())
    println(list.joinToString(" - ", "[", "]", 2))
}


//----------Interfaces------

//declare an interface
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable") //Method with a default implementation
}

//implementing an interface
class Button : Clickable {
    override fun click() {
    }
}

//----------Abstract class and method----------
abstract class Animated { //Cant create an instance of this class, only extend this class

    abstract fun animate() //Doesn't have an implementation must be overridden (open by default)

    fun animateTwice() { //close by default
        //...
    }
}

//----------Nested and Inner Class----------
class View {

    //Nested
    class ViewState {
        //This nested class is analogue of a static nested class in Java
        //Does not reference the outer class
    }

    //Inner
    inner class Inner {
        fun getOuterReference() = this@View //Reference the instance of the outer class
    }
}

//Correspondence between nested and inner classes in Java and Kotlin
//+------------------------------------------------------------+----------------+---------------+
//| Class A declared within another class B                    | In Java        | In kotlin     |
//+------------------------------------------------------------+----------------+---------------+
//| Nested class (doesn't store a reference to an outer class) | static class A | class A       |
//+------------------------------------------------------------+----------------+---------------+
//| Inner class (stores a reference to an outer class)         | class A        | inner class A |
//+------------------------------------------------------------+----------------+---------------+


//----------Sealed Class----------
//You mark a superclass with sealed modifier, an that restricts the possibility of creating subclasses.
sealed class Expr {

    // All the direct subclasses must be nested in the superclass
    class Sum : Expr()
}

//----------Access modifiers----------

//+----------+--------------------------------------------+-------------------------------------------------------------------------------------+
//| Modifier | Corresponding member                       | Comments                                                                            |
//+----------+--------------------------------------------+-------------------------------------------------------------------------------------+
//| final    | Can't be overridden                        | Used by default for class members                                                   |
//+----------+--------------------------------------------+-------------------------------------------------------------------------------------+
//| open     | Can be overridden                          | Should be specified explicitly                                                      |
//+----------+--------------------------------------------+-------------------------------------------------------------------------------------+
//| abstract | Must be overridden                         | Can be used only in abstract classes; abstract members can't have an implementation |
//+----------+--------------------------------------------+-------------------------------------------------------------------------------------+
//| override | Overrides a member in a class or interface | Overridden member is open by default, if not marked final                           |
//+----------+--------------------------------------------+-------------------------------------------------------------------------------------+


////----------Visibility modifiers----------

//+------------------+-----------------------+-----------------------+
//| Modifier         | Class member          | Top-level declaration |
//+------------------+-----------------------+-----------------------+
//| public (default) | Visible everywhere    | Visible everywhere    |
//+------------------+-----------------------+-----------------------+
//| internal         | Visible in a module   | Visible in a module   |
//+------------------+-----------------------+-----------------------+
//| protected        | Visible in subclasses | -                     |
//+------------------+-----------------------+-----------------------+
//| private          | Visible in a class    | Visible in a file     |
//+------------------+-----------------------+-----------------------+


//---------- Class constructors ----------

//primary constructor
class User(val nickaname: String)

class User constructor(nickname: String) {
    val nickname: String = nickname
}

class User constructor(nickname: String) {
    val nickname: String

    init { //initializer block
        this.nickname = nickname
    }
}

//the most concise syntax
class User(val nickname: String, val isSubscribed: Boolean)

//with default parameters values
class User(val nickname: String = "unknown", val isSubscribed: Boolean = false)


//secondary constructor
class User {
    constructor(nickname: String) {

    }

    constructor(nickname: String, isSubscribed: Boolean) {

    }
}


//---------- data class ----------
data class Client(val name: String, val postalCode: Int)

//Overrides:
//- [equals] for comparison instances
//- [hasCode] for using them as keys in hash-based containers such as HashMap
//- [toString] for generating string representations showing all the fields in declaration order


//Copy method
// it is used to copy and modify (change values) of the instance in one place
// it has a separate lifecycle and doesn't effect the original instance of the object

fun copyTest() {
    val bob = Client("Bob", 1234)
    println(bob.copy(postalCode = 9999))
}
//output: Client(name=Bob, postalCode=9999)


//---------- Class delegation using 'by' keyword
class myCollection<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {
    //Delegates the  Collection implementation to innerList

    //Compiler will generate all the needed method implementations

    //If we need to change the behavior of a method we can override it and that code will be called instead of the original method
}
