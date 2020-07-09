package ayds.cuckoo1.nyt.external

import ayds.cuckoo1.nyt.external.entities.NYTDBMovieResponse

interface Service {
    fun getMovie(title: String, year: String): NYTDBMovieResponse
    companion object{
       fun getInstance(): Service = NYTDataModule.externalService
    }
}