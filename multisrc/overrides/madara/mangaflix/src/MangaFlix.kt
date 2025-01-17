package eu.kanade.tachiyomi.extension.ar.mangaflix

import eu.kanade.tachiyomi.multisrc.madara.Madara
import eu.kanade.tachiyomi.source.model.SChapter
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Locale

class MangaFlix : Madara(
    "مانجا فليكس",
    "https://www.manga-flix.com",
    "ar",
    dateFormat = SimpleDateFormat("dd/MM/yyy", Locale.ROOT)
) {
    override val useNewChapterEndpoint = true

    override val mangaDetailsSelectorStatus = "div.summarys"

    override fun chapterFromElement(element: Element): SChapter {
        val chapter = super.chapterFromElement(element)

        with(element) {
            select(chapterUrlSelector).first()?.let { urlElement ->
                // use .ownText() instead of .text()
                chapter.name = urlElement.ownText()
            }
        }

        return chapter
    }
}
