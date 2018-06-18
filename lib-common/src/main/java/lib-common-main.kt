import common.getClassProperties

data class DataClassForReflection(val number: Number, val string: String, val recursive: DataClassForReflection)

fun main(args: Array<String>) {
  println("!!! LIB-COMMON main(): FOR TESTING PURPOSES ONLY !!!")

  val clazz = DataClassForReflection::class.java
  println("Class properties for ${clazz.name}: ${getClassProperties(clazz).mapValues { it.value.name }}")
}
