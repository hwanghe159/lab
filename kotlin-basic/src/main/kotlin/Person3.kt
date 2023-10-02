import ru.yole.jkid.DeserializeInterface

data class Person3(
    val name: String,
    @DeserializeInterface(CompanyImpl::class) val company: Company
)