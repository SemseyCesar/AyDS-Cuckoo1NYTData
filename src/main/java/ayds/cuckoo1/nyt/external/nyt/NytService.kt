package ayds.cuckoo1.nyt.external.nyt

import ayds.cuckoo1.nyt.external.Service
import ayds.cuckoo1.nyt.external.entities.NYTDBMovieResponse
import ayds.cuckoo1.nyt.external.entities.OmdbMovieResponse
import retrofit2.Response

class NytService(
    private val nytdbMovieHandler: ExternalMovieHandler,
    private val nytAPI: NYTimesAPI
) : Service {

    override fun getMovie(movie: OmdbMovieResponse): NYTDBMovieResponse {
        val response = getMovieResponse(movie.title)
        return nytdbMovieHandler.handleResponse(response.body(), movie)
    }

    private fun getMovieResponse(movieTitle: String): Response<String> {
        return nytAPI.getTerm(movieTitle).execute()
    }
}