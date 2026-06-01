package assesment1

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

private val httpClient = OkHttpClient()
private val HEADER = listOf("x-coordinate", "Character", "y-coordinate")

private fun fetchHtmlDocument(url: String): Document? {
    val req =
        Request
            .Builder()
            .url(url)
            .get()
            .build()
    return try {
        httpClient.newCall(req).execute().use { resp ->
            val html = resp.body?.string() ?: return null
            Jsoup.parse(html)
        }
    } catch (_: Exception) {
        null
    }
}

private fun parseTableFromHtml(doc: Document): List<Triple<Int, Int, String>> {
    val table = doc.selectFirst("table") ?: return emptyList()
    val rows = table.select("tr")
    if (rows.isEmpty()) return emptyList()

    // build a list of rows as lists of cell text
    val cells = rows.map { r -> r.select("td,th").map { it.text().trim() } }
    val hasHeader =
        cells.firstOrNull()?.let { first ->
            (0 until minOf(first.size, HEADER.size)).all { idx -> first.getOrNull(idx)?.equals(HEADER[idx], ignoreCase = true) == true }
        } ?: false

    val dataRows = if (hasHeader) cells.drop(1) else cells

    return dataRows.mapNotNull { cols ->
        if (cols.size < 3) return@mapNotNull null
        val x = cols[0].toIntOrNull()
        val ch = cols[1]
        val y = cols[2].toIntOrNull()
        if (x != null && y != null) Triple(x, y, ch) else null
    }
}

fun parseTableToStrings(docUrl: String): List<String>? {
    val htmlDoc = fetchHtmlDocument(docUrl) ?: return null
    val triples =
        parseTableFromHtml(htmlDoc).takeIf { it.isNotEmpty() }
            ?: return null

    return triples
        .groupBy { it.second } // group by y
        .map { (y, group) ->
            val charMap = group.associateBy { it.first }
            val minX = group.minOf { it.first }
            val maxX = group.maxOf { it.first }
            val line = (minX..maxX).map { x -> charMap[x]?.third ?: " " }.joinToString("")
            y to line
        }.sortedBy { -it.first } // sort by y descending
        .map { it.second }
}

fun main() {
    val url = "https://docs.google.com/document/d/e/2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub"
    val rows = parseTableToStrings(url)
    if (rows.isNullOrEmpty()) {
        println("Unable to retrieve or parse document table. Ensure it is published and contains the expected table.")
        return
    }
    println("Parsed ${rows.size} lines:")
    rows.forEach { 
        println(it)
    }
}
