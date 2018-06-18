package handler

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import util.handleExchangeWithPayload
import util.toDocument
import util.toElement

class HandleStub : HttpHandler {
  override fun handle(http: HttpExchange?) = with(http!!) {
    responseHeaders.set("Content-Type", "text/html; charset=UTF-8")
    handleExchangeWithPayload(this, toDocument(toElement("h1", "STUB")))
  }
}
