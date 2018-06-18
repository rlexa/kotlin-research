import common.getClassProperties
import entity.Person

fun main(args: Array<String>) {
  println("!!! LIB-DATA-ENTITY main(): FOR TESTING PURPOSES ONLY !!!")

  println("Data Classes:")
  listOf(Person::class.java)
    .sortedBy { it.name }
    .forEach { println("\t${it.name} = ${getClassProperties(it).mapValues { it.value.name }}") }
}
