import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun main(args: Array<String>) {
    val javaObjectMapper: ObjectMapper = ObjectMapper()
    val kotlinObjectMapper: ObjectMapper = jacksonObjectMapper()



    // Record _without_ primitive type
    val recordWithoutPrimitive = RecordWithoutPrimitive("1234abc", 42)
    val recordWithoutPrimitiveString = """{"id":"1234abc","nonPrimitive":42}"""

    // Java object mapper
    val recordWithoutPrimitiveJava: RecordWithoutPrimitive =
        javaObjectMapper.readValue(recordWithoutPrimitiveString, RecordWithoutPrimitive::class.java)
    assert(recordWithoutPrimitiveJava == recordWithoutPrimitive)

    // Kotlin object mapper
    val recordWithoutPrimitiveKotlin: RecordWithoutPrimitive =
        kotlinObjectMapper.readValue(recordWithoutPrimitiveString, RecordWithoutPrimitive::class.java)
    assert(recordWithoutPrimitiveKotlin == recordWithoutPrimitive)



    // Record _with_ primitive type
    val recordWithPrimitive = RecordWithPrimitive("1234abc", 42)
    val recordWithPrimitiveString = """{"id":"1234abc","primitive":42}"""

    // Java object mapper
    val recordWithPrimitiveJava: RecordWithPrimitive =
        javaObjectMapper.readValue(recordWithPrimitiveString, RecordWithPrimitive::class.java)
    assert(recordWithPrimitiveJava == recordWithPrimitive)

    // Kotlin object mapper
    // THIS FAILS ON JACKSON DATABIND 17!
    val recordWithPrimitiveKotlin: RecordWithPrimitive =
        kotlinObjectMapper.readValue(recordWithPrimitiveString, RecordWithPrimitive::class.java)
    assert(recordWithPrimitiveKotlin == recordWithPrimitive)

    println("All passed!")
}