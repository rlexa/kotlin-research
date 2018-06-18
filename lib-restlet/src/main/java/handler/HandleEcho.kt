package handler

import com.sun.net.httpserver.HttpExchange
import util.getQuery
import util.toElements

class HandleEcho : AbstractHandleSubMain {
  constructor()

  override fun provideHtmlBody(http: HttpExchange?) = with(http!!) {
    toElements(listOf(
      "h1" to "Echo",
      "h4" to "Query:",
      "ul" to toElements(getQuery(http).map { "li" to "${it.first} = ${it.second}" })
    ))
  }
}
