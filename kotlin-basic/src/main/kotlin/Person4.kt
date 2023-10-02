import ru.yole.jkid.CustomSerializer
import java.util.*

data class Person4(
    val name: String,
    @CustomSerializer(DateSerializer::class) val birthDate: Date
)