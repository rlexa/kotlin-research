package common

fun getClassProperties(from: Class<*>) = from.declaredFields
  .filter { it.name != null && it.type != null }
  .map { it.name as String to it.type as Class<*> }
  .sortedBy { it.first }
  .toMap()