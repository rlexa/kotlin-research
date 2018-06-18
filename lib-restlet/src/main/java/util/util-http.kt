package util

import com.sun.net.httpserver.HttpExchange

fun getQuery(http: HttpExchange) = with(http) {
  (requestURI.query ?: "")
    .split("&")
    .filter { it.isNotEmpty() }
    .map {
      val ii = it.indexOf("=")
      Pair(it.substring(0, ii).trim(), it.substring(ii + 1).trim())
    }
    .filter { it.first.isNotEmpty() }
}

fun handleExchangeWithPayload(http: HttpExchange, data: String) = with(http) {
  sendResponseHeaders(200, data.toByteArray(Charsets.UTF_8).size.toLong())
  with(responseBody) {
    write(data.toByteArray(Charsets.UTF_8))
    close()
  }
}
