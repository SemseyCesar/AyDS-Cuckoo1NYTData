# AyDS-Cuckoo1NYTData

Este módulo provee la función de obtener información(reviews) sobre determinada película por medio de la api ofrecida por el New York Times.

#### Pasos para obtener una review sobre un película en New York Times:
* Obtener el objeto Service, por medio del singleton NYTDataModule:
    
        NYTDataModule.externalService
        
* Luego es necesario crear un objeto **OmdbMovieResponse**, el cual contiene el título y fecha de la película.

                val omdbMovieResponse = OmdbMovieResponse()
                omdbMovieResponse.title = "The Matrix"
                omdbMovieResponse.year = "1999"                

* Luego se invoca el método **getMovie** al **externalService**, suministrándole el objeto de tipo **OmdbMovieResponse**.

        val nytMovieResponse = externalService.getMovie(omdbMovieResponse)
        
* Se obtendrá un objeto de tipo NYTDBMovieResponse con la información(review) de la película.
