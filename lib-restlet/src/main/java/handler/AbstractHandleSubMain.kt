package handler

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import util.handleExchangeWithPayload
import util.toDocument
import util.toElements

abstract class AbstractHandleSubMain : HttpHandler {
  override fun handle(http: HttpExchange?) = with(http!!) {
    responseHeaders.set("Content-Type", "text/html; charset=UTF-8")
    handleExchangeWithPayload(this, toDocument(toElements(listOf(
      "a href=\"/\"" to "home",
      "div" to (provideHtmlBody(this))))))
  }

  abstract fun provideHtmlBody(http: HttpExchange?): String
}