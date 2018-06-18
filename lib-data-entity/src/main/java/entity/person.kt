package entity

data class Person(val id: Long, val name: String, val surname: String, val friends: List<Long>)
