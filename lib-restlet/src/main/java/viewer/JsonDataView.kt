package viewer

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class JsonDataView : IDataView {
  private val dateFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneOffset.UTC)

  override fun transform(data: Any): String = toJsonPart(data)

  private fun toJsonPart(from: Any?): String = when (from) {
    is Array<*> -> toJsonPart(from.asIterable())
    is Boolean -> from.toString()
    is Date -> toJsonPart(from.toInstant())
    is Instant -> toJsonPart(dateFormatter.format(from))
    is Iterable<*> -> "[${from.map { toJsonPart(it) }.joinToString()}]"
    is Map<*, *> -> "{${from.map { "\"${it.key}\": ${toJsonPart(it.value)}" }.joinToString()}}"
    is Number -> from.toString()
    is Pair<*, *> -> toJsonPart(mapOf(from))
    is String -> "\"${from.replace("\n", "\\n").replace("\t", "\\t")}\""
    else -> if (from == null) "null" else "\"$from\""
  }
}