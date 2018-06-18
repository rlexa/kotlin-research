package util

fun extractTag(from: String) = from.split(" ")[0]
fun extractAttributes(from: String) = with(from.indexOf(' ')) {
  if (this < 1) "" else from.substring(1)
}

fun toDocument(data: String) =
  "<!doctype html>\n" +
    "<html lang=\"en\">\n" +
    "<head>\n" +
    "  <!-- angular router root -->\n" +
    "  <base href=\"/\">\n" +
    "  <meta charset=\"utf-8\">\n" +
    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
    "  <title>Walker</title>\n" +
    "</head>\n" +
    "<style>\n" +
    "  body { font-family: monospace; }\n" +
    "  td { padding: .2rem .5rem .2rem .5rem; vertical-align: top; }\n" +
    "  ul { margin: 0rem; padding: 0rem; padding-left: 1rem; }\n" +
    "</style>\n" +
    "<body>\n" +
    "  $data\n" +
    "</html>"

fun toElements(data: Collection<Pair<String, Any?>>) = data
  .map { "<${extractTag(it.first)}${extractAttributes(it.first)}>${it.second?.toString()}</${extractTag(it.first)}>" }
  .joinToString("")

fun toElement(element: String, data: Any?) = toElements(listOf(element to data))
fun toElement(elementData: Pair<String, Any?>) = toElements(listOf(elementData))