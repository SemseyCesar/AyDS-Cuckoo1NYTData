package ayds.cuckoo1.nyt.external.nyt

import ayds.cuckoo1.nyt.external.entities.NYTDBMovieResponse
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

interface ExternalMovieHandler {
    fun handleResponse(responseBody: String?, title: String, year: String): NYTDBMovieResponse
}

class ExternalMovieHandlerImpl() : ExternalMovieHandler {

    private val nullJSONObject = JsonObject()
    private val NO_RESULTS = "No Results"
    private val DEFAULT_NYTIMES_IMAGE_URL = "https://www.shareicon.net/data/256x256/2016/06/25/618683_new_256x256.png"
    private val MULTIMEDIA_JSON = "multimedia"
    private val SUMMARY_JSON = "summary_short"
    private val SOURCE_JSON = "src"
    private val PUBLICATION_DATE_JSON = "publication_date"
    private val LINK_JSON = "link"

    override fun handleResponse(responseBody: String?, title: String, year: String): NYTDBMovieResponse {
        val nytdbMovie = NYTDBMovieResponse()

        val movieInfoJSON = getMovieInfo(responseBody, year)
        nytdbMovie.apply {
            this.title = title
            summary = getMovieSummaryFromJSON(movieInfoJSON)
            reviewUrl = getMovieReviewUrlFromJSON(movieInfoJSON)
            imageUrl = getImageUrlFromJSON(movieInfoJSON)
        }
        return nytdbMovie
    }

    private fun getMovieInfo(responseBody: String?, movieYear: String): JsonObject {
        try {
            return getMovieInfoFromJSON(responseBody, movieYear)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return nullJSONObject
    }

    private fun getMovieInfoFromJSON(responseBody: String?, movieYear: String): JsonObject {
        val gson = Gson()
        val jObj = gson.fromJson(responseBody, JsonObject::class.java)
        val resultIterator = jObj.get("results").asJsonArray.iterator()
        var result = nullJSONObject
        while (resultIterator.hasNext()) {
            result = resultIterator.next().asJsonObject
            val year = result.get(PUBLICATION_DATE_JSON).asString.split("-")[0]
            if (year == movieYear) break
        }
        return result
    }

    private fun getMovieSummaryFromJSON(movieInfoJSON: JsonObject): String {
        if (movieInfoJSON === nullJSONObject) return NO_RESULTS
        return getJSONSummary(movieInfoJSON).asString.replace("\\n", "\n")
    }

    private fun getMovieReviewUrlFromJSON(movieInfoJSON: JsonObject): String {
        if (movieInfoJSON === nullJSONObject) return NO_RESULTS
        val reviewUrl = getJSONLink(movieInfoJSON)
        return reviewUrl.asString
    }

    private fun getJSONLink(movieInfoJSON: JsonObject): JsonElement {
        return movieInfoJSON.get(LINK_JSON).asJsonObject.get("url")
    }

    private fun getJSONSummary(movieInfoJSON: JsonObject): JsonElement {
        return movieInfoJSON.get(SUMMARY_JSON)
    }

    private fun getImageUrlFromJSON(movieInfoJSON: JsonObject): String {
        val multimedia = getMultimediaFromJSON(movieInfoJSON)
        var imageUrl = DEFAULT_NYTIMES_IMAGE_URL
        if (multimedia !== nullJSONObject) imageUrl = multimedia.get(SOURCE_JSON).asString
        return imageUrl
    }

    private fun getMultimediaFromJSON(result: JsonObject): JsonObject {
        if (result === nullJSONObject) return nullJSONObject
        val multimediaJson = result.get(MULTIMEDIA_JSON)
        return if (multimediaJson.isJsonObject) {
            multimediaJson.asJsonObject
        } else
            nullJSONObject
    }
}