package com.kotlin

open class Person(val name: String) {
    var last:String? = null

    constructor(name: String, last: String): this(name) {
        println("second constructor")
        this.last = last
        println("constructor last = $last")
    }

    val customerKey = name.toUpperCase()

    var first = "First property: $name".also(::println)

    init {
        println("first initializer block that print $name")
        println("last = $last")
    }

    var secont = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that print ${name.length}")
    }

    open fun hello() {
        println("base hello $name")
    }
}

class ExtendPerson(name: String) : Person(name, "") {

    init { println("Initializing Derived") }
    override fun hello() {
        super.hello()
        println("extend hello")
    }
}
