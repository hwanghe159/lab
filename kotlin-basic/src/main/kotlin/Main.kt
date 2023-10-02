import ru.yole.jkid.deserialization.deserialize
import ru.yole.jkid.serialization.serialize
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    println(serialize(person))

    val json = """{"name":"Alice", "age":29}"""
    println(deserialize<Person>(json))

    val person2 = Person2("junho", 28)
    println(serialize(person2))

    val json2 = """{"alias": "junho"}"""
    println(deserialize<Person2>(json2))

    val person3 = Person3("Alice", CompanyImpl("backpackr"))
    println(serialize(person3))

    val json3 = """{"company": {"name": "backpackr"}, "name": "junho"}"""
    println(deserialize<Person3>(json3))

    val person4 = Person4("junho", SimpleDateFormat("dd-mm-yyyy").parse("14-03-1995"))
    println(serialize(person4))
}