import Color.*

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
    when (color) {
        RED -> "빨"
        ORANGE -> "주"
        YELLOW -> "노"
        GREEN -> "초"
        BLUE -> "파"
        INDIGO -> "남"
        VIOLET -> "보"
    }

fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }