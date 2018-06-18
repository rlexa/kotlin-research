package viewer

import util.toDocument
import util.toElement
import util.toElements
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class HtmlDataView : IDataView {
  private val dateFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneOffset.UTC)

  override fun transform(data: Any): String = toHtml(data)
  private fun toHtml(from: Any) = toDocument(toHtmlPart(from))
  private fun toHtmlPart(from: Any?): String = toElement(when (from) {
    is Array<*> -> "ul" to toElements(from.map { "li" to toHtmlPart(it) })
    is Date -> "label" to dateFormatter.format(from.toInstant())
    is Instant -> "label" to dateFormatter.format(from)
    is Iterable<*> -> "ul" to toElements(from.map { "li" to toHtmlPart(it) })
    is Map<*, *> -> "table" to toElements(from.map {
      "tr" to toElements(listOf(
        "td" to toElement("b" to (it.key ?: "").toString()),
        "td" to toHtmlPart(it.value)))
    })
    is Pair<*, *> -> "span" to toElements(listOf(
      "b" to (from.first ?: "").toString() + " = ", "span" to toHtmlPart(from.second)))
    is String -> "label" to from.replace("\n", "<br>")
    else -> "label" to (from ?: "null").toString()
  })
}