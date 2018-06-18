package handler

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import resolver.IQueryResolver
import util.getQuery
import util.handleExchangeWithPayload
import viewer.IDataView

class HandleGeneric(
  private val source: IQueryResolver,
  private val view: IDataView,
  private val contentType: String = "text/html; charset=UTF-8"
) : HttpHandler {
  override fun handle(http: HttpExchange?) = with(http!!) {
    responseHeaders.set("Content-Type", contentType)
    handleExchangeWithPayload(this, view.transform(source.resolve(listOf(), getQuery(this))))
  }
}