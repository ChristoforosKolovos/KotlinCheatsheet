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

    //Destructuring declaration
    for ((key, value) in map) {
        println("$key - $value")
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

//foreach
items.forEach {
    println(it.name)
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
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> {
            print("x is neither 1 nor 2")
        }
    }

    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }

    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }

    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbersCollection -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }

    when {
        x.isOdd() -> print("x is odd")
        x.isEven() -> print("x is even")
        else -> print("x is funny")
    }
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

//Delegated properties
class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

//Lazy
//lazy() is a function that takes a lambda and returns an instance of Lazy<T>
//which can serve as a delegate for implementing a lazy property: the first call to get()
//executes the lambda passed to lazy() and remembers the result, subsequent calls to get() simply return the remembered result.

val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main(args: Array<String>) {
    println(lazyValue)
    println(lazyValue)
}

//This example prints:
//computed!
//Hello
//Hello


//Observable
//Delegates.observable() takes two arguments: the initial value and a handler for modifications.
//The handler gets called every time we assign to the property (after the assignment has been performed).
//It has three parameters: a property being assigned to, the old value and the new one:

class User {
    var name: String by Delegates.observable("<no name>") { property, oldValue, newValue ->
        println("$oldValue -> $newValue")
    }
}

fun main(args: Array<String>) {
    val user = User()
    user.name = "first"
    user.name = "second"
}

//This example prints:
//<no name> -> first
//first -> second


//Storing Properties in a Map using delegation
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}


//---------- the 'object' keyword ----------
//Defines class and creatres an instance

//Its used for:

//- [Object declaration] Defining a singleton

//- [Companion objects] can contain factory methods and other methods that are related to this class but don't require a class instance to be called.
//  Their members can be accesed via class name. Like static factory method [!?: why this and not inner?]
class A {
    companion object {
        fun bar() {
            //...
        }
    }
}

//Extension for companion object
fun A.Companion.myExtension() {
    //...
}

//- [Object expression] is used instead of Java's anonymous inner class
fun objectTest() {
    view.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            //...
        }
    })
}

//---------- Nullabillity----------
//It's a way to indicate which variables or properties are allowed to be null.
val s: String?

//Handling null values using if checks
fun strLenSafe(s: String?) = if (s != null) s.length else 0

//Safe call operator: '?.'
fun strLenSafe(s: String?) = s?.length else 0

//Chaining multiple safe-call operators
val country = company?.adress?.country

//Elvis operator ?:
//Provide default values isntead of null
val country = company?.adress?.country ?: "Unknown"

//Safe casts: 'as?'
//Casts a value to the given type and returns null if the type differs
val isTypePerson = x as? Person ?: return false //compined with elvis operator to return false instead of null

//Not-null assertion: '!!'
//You are telling the compiler that you know that the value is not null.
//This may be used when you check for null in one function, then use the value in another,
//where there is no need to check there too, and you need to stop the compiler error,
//because the compiler cannot know if the use is safe.
//If finally for any reason a null value is passed to the 2nd function an exception will be thrown.
val sNotNull: String = s!!


//---------- Lamdas ----------

fun lamdaTest() {

    val sum = { x: Int, y: Int -> x + y }
    sum(1, 2)

    //------------------

    val l = { println("hello") }
    l

    //------------------

    run { println("hello") }

    //------------------

    run {
        if (firstTimeView) introView else normalView
    }.show()

    //------------------

    webview.settings.run {
        javaScriptEnabled = true
        databaseEnabled = true
    }


    //'let' and 'also'
    //Handling a nullable argument that should be passed to a function that expects non-null parameter

    //returns a different type of value
    stringVariable?.let {
        println("The length of this String is ${it.length}")
    }

    //returns the T itself i.e. this
    stringVariable?.also {
        println("The length of this String is ${it.length}")
    }


    //---------- Lamdas with receivers - 'with' and 'apply'----------
    //Perform multiple operations on the same object without repeating its name

    //----with----
    //You can use it when you need to perform some operations on an object and return some other object
    fun getPersonFromDeveloper(developer: Developer): Person {
        return with(developer) {
            Person(developerName, developerAge)
        }
    }

    //----apply----
    //You can use it when you need to do something with an object and return it (creating builders)
    fun getPerson(): Person {
        return Person().apply {
            name = "John"
            age = 22
        }
    }

    //---- Functions Usage ----

    class MyClass {
        fun test() {
            val str: String = "..."

            val result = str.xxx {
                print(this) // Receiver
                print(it) // Argument
                42 // Block return value
            }
        }
    }

    //╔══════════╦═════════════════╦═══════════════╦═══════════════╗
    //║ Function ║ Receiver (this) ║ Argument (it) ║    Result     ║
    //╠══════════╬═════════════════╬═══════════════╬═══════════════╣
    //║ let      ║ this@MyClass    ║ String("...") ║ Int(42)       ║
    //║ run      ║ String("...")   ║ N\A           ║ Int(42)       ║
    //║ run*     ║ this@MyClass    ║ N\A           ║ Int(42)       ║
    //║ with*    ║ String("...")   ║ N\A           ║ Int(42)       ║
    //║ apply    ║ String("...")   ║ N\A           ║ String("...") ║
    //║ also     ║ this@MyClass    ║ String("...") ║ String("...") ║
    //╚══════════╩═════════════════╩═══════════════╩═══════════════╝
    //* those functions are not extension methods, they need to be called the old-fashioned way

    //------------------

    val people = listOf(Person("Alice"), Person("John"))

    //Lamda expression example without using shortcuts
    people.maxBy({ p: Person -> p.name })

    //Expression out of parentheses if it's the last argument in function call
    people.maxBy() { p: Person -> p.name }

    //Omit parentheses if  it's the only argument to a function
    people.maxBy { p: Person -> p.name }

    //parameter type inferred (always start without the types. If the compiler complains, specify them)
    people.maxBy { p -> p.name }

    //default autogenerated parameter name
    people.maxBy { it.name }

    //lamda in a variable
    val getName = { p: Person -> p.name }
    people.maxBy(getName)


    //---- Member references----
    //You can use the '::' operator to convert a function to a value
    // It is creating a function value that calls exactly one method or accesses a property

    val getName = Person::name
    //it is equal to:
    val getName = { p: Person -> p.name }

    //-----------------

    fun salute() = println("hello")
    run(::salute)

}

//---------- Functional APIs for collections----------

//----filter----
//Selects the elemetns for which the given lambda returns true
list.filter{ it % 2 == 0 }

//----map----
//Applies the given lambda to each elemt
list.map{ it * it } //transforms the list memebrs into a list of their square
list.map{ it.name } //if you want to print just a list of names

//----all----
//Check if all elements satify the give expression
people.all{ p:Person -> p.age<=27 }

//----any----
//Check if at least one element satifies the give expression
people.any{ p:Person -> p.age<=27 }

//----count----
//if you want to know how many elemnts satisfy the given expression
people.count{ p:Person -> p.age<=27 }

//----find----
//To find the elements that satisfy the given expression
people.find{ p:Person -> p.age<=27 }


//----groupBy----
//Converts a list to a map of groups
people.groupBy{ it.age } //more in page 117


//----flatMap----
//It transforms (maps) to a collection according to lambda and
//it combines (flatterns) several lists into one

val strings listOf("abc", "def")
println(strings.flatMap{ it.toList() }) //we can use 'toSet()' instead of 'toList()' to remove duplicates
//output: [a,b,c,d,e,f]


//----Sequence----
//You can use sequences to efficiently perform chains of operations on elements of a collection 
//without creating collections to hold intermediated results of the proccessing.
//We can convert any collection to a sequence by calling the extension function 'asSequence()'
//and convert it back to list using 'toList()'

people.asSequence()
.map(Person::name)
.filter{ it.startsWith("A") }
.toList()


//---------- Operators Overloading----------


operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

//usage
val p1 = Point(10, 20)
val p2 = Point(30, 40)
println(p1+p2)
//output: Point(x=40,y=60)

//Overloadable binary arithmetic operators
// '*' = times
// '/' = div
// '%' = mode
// '+' = plus
// '-' = minus

//Overloadable unary operators
// +a = unaryPlus
// -a = unaryMinus
// !a = not
// ++a, a++ = inc
// --a, a-- = dec

//Equals overload operator
// equals = '=='

//Note: '===' is the same like java's '==' and it checks that both of its arguments reference the same object
//and cannot be overloaded


//'get' overloading
operator fun Point.get(index: int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException()
    }
}

//usage
println(p[1])

//'set' overloading
operator fun Point.set(index: int, value: Int) {
    return when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException()
    }
}

//usage
p[1] = 23


//'in' overloading
operator fun Rectangle.contains(p: Point): Boolean {
    //...
}

//usage
Point(5,5) in rect


//'rangeTo'
operator fun <T : Comparable<T>> T.rangeTo(that: T): ClosedRange<T>

//usage
val range = x..y


//'invoke'
class myClass {
    operator fun invoke() {
        //...
        //can also be written as extension likt the other operators
    }
}

//usage
val x = myClass()
//way 1
x.invoke()
//way 2
x()
