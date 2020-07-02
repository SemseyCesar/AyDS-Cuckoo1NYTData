package ayds.cuckoo1.nyt.external

import ayds.cuckoo1.nyt.external.entities.NYTDBMovieResponse
import ayds.cuckoo1.nyt.external.entities.OmdbMovieResponse

interface Service {
    fun getMovie(movie: OmdbMovieResponse): NYTDBMovieResponse
}