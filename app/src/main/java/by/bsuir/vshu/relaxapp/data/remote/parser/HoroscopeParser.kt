package by.bsuir.vshu.relaxapp.data.remote.parser


import by.bsuir.vshu.relaxapp.data.remote.dto.HoroscopeDto
import by.bsuir.vshu.relaxapp.util.Constants
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class HoroscopeParser {

    suspend fun getItem(): HoroscopeDto {

        return parse(Constants.HOROSCOPE_URL)
    }

    private suspend fun parse(url: String): HoroscopeDto {

        val doc = Jsoup.connect(url)
            .referrer("http://www.google.com")
            .get()


        val date = getDate(doc)
        val description = getDescription(doc)
        println("date: $date")
        println("desc: $description")

        return HoroscopeDto(0, date, description)
    }


    private fun getDate(element: Element): String {
        return element.getElementsByClass("p-prediction__date__text").text()
    }

    private fun getDescription(element: Element): String {
        return element.getElementsByClass("article__text").text()
    }

}