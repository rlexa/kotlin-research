package handler

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import util.handleExchangeWithPayload
import util.toDocument
import util.toElement
import util.toElements

class HandleMain : HttpHandler {
  override fun handle(http: HttpExchange?) = with(http!!) {
    responseHeaders.set("Content-Type", "text/html; charset=UTF-8")
    handleExchangeWithPayload(this, toDocument(toElements(listOf(
      "h1" to "Home",
      "table" to toElements(listOf(
        "tr" to toElements(listOf("th" to "Route", "th" to "Info")),
        "tr" to toElements(listOf("td" to toElement("a href=\"/echo?a=1&b=2&c=3\"", "/echo"), "td" to "Echo service")),
        "tr" to toElements(listOf("td" to toElement("a href=\"/generic/html\"", "/generic/html"), "td" to "Generic html view"))
      ))))))
  }
}