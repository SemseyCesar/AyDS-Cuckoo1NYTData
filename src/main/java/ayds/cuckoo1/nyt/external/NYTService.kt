package ayds.cuckoo1.nyt.external

import ayds.cuckoo1.nyt.external.entities.NYTDBMovieResponse

interface NYTService {
    fun getMovie(title: String, year: String): NYTDBMovieResponse
    companion object{
       fun getInstance(): NYTService = NYTDataModule.externalService
    }
}