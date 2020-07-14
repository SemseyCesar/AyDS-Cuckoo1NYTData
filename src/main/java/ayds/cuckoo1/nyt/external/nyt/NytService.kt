package ayds.cuckoo1.nyt.external.nyt

import ayds.cuckoo1.nyt.external.NYTService
import ayds.cuckoo1.nyt.external.entities.NYTDBMovieResponse
import retrofit2.Response

internal class NytService(
    private val nytdbMovieHandler: ExternalMovieHandler,
    private val nytAPI: NYTimesAPI
) : NYTService {

    override fun getMovie(title: String, year: String): NYTDBMovieResponse {
        val response = getMovieResponse(title)
        return nytdbMovieHandler.handleResponse(response.body(), title, year)
    }

    private fun getMovieResponse(movieTitle: String): Response<String> {
        return nytAPI.getTerm(movieTitle).execute()
    }
}