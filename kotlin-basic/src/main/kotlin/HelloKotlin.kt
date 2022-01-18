
fun main(args: Array<String>) {
    val persons = listOf(Person("영희", 24, true), Person("철수", age = 20, false))
    val oldest = persons.maxByOrNull { it.age ?: 0 }
    println("나이가 가장 많은 사람: $oldest")
    println(RED.rgb())
    val price = 1000
    println("총 가격은 $price\$")
    val name = "준호"
    println("${name}님 반가워요")
    println("Hello, ${if (args.size > 0) args[0] else "someone"}!")
    val rectangle = Rectangle(41, 43)
    println(getKorean(VIOLET))

}

fun getKorean(color: Color) =
