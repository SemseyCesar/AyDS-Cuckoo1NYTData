# AyDS-Cuckoo1NYTData

Este módulo provee la función de obtener información(reviews) sobre determinada película por medio de la api ofrecida por el New York Times.

#### Pasos para obtener una review sobre un película en New York Times:
* Obtener una instancia de Service, por medio del método getInstance():
    
        externalService = Service.getInstance()
                      

* Luego se invoca el método **getMovie** al **externalService**, suministrándole el título y año de la película.

        val nytMovieResponse = externalService.getMovie("The Matrix", "1999")
        
* Se obtendrá un objeto de tipo NYTDBMovieResponse con la información(review) de la película.
