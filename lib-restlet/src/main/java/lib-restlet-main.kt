import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import handler.HandleEcho
import handler.HandleGeneric
import handler.HandleMain
import resolver.QueryResolverStatic
import viewer.HtmlDataView
import viewer.JsonDataView
import java.net.InetSocketAddress
import java.util.*

fun startServer(port: Int, handlers: Map<String, HttpHandler>) {
  with(HttpServer.create(InetSocketAddress(port), 0)) {
    println("Server: registering ${handlers.size} routes...")
    handlers.forEach {
      println("Server:\t\t${it.key} <--> ${it.value.javaClass.name}")
      createContext(it.key, it.value)
    }
    println("Server: ...ok, starting...")
    start()
  }
}

fun main(args: Array<String>) {
  println("!!! LIB-RESTLET main(): FOR TESTING PURPOSES ONLY !!!")

  val genericTestObject = mapOf(
    "array" to arrayOf(1, 2),
    "boolean" to true,
    "date" to Date(),
    "list" to listOf(1, 2),
    "listOfPairs" to listOf(1 to "One", 2 to "Two"),
    "map" to mapOf(1 to "One", 2 to "Two"),
    "null" to null,
    "number" to 12.345,
    "pair" to (1 to "One"),
    "string" to "Hello!",
    "stringLines" to "Hello!\nAnother line!"
  )

  val routes = mapOf(
    "/" to HandleMain(),
    "/echo" to HandleEcho(),
    "/generic/html" to HandleGeneric(QueryResolverStatic(genericTestObject), HtmlDataView()),
    "/generic/json" to HandleGeneric(QueryResolverStatic(genericTestObject), JsonDataView(), "application/json")
  )

  startServer(8080, routes)
}
