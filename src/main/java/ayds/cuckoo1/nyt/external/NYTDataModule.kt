package ayds.cuckoo1.nyt.external

import ayds.cuckoo1.nyt.external.nyt.ExternalMovieHandlerImpl
import ayds.cuckoo1.nyt.external.nyt.NYTimesAPI
import ayds.cuckoo1.nyt.external.nyt.NytService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object NYTDataModule {
    private const val RETROFIT_URL = "https://api.nytimes.com/svc/movies/v2/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(RETROFIT_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
    val externalService: Service = NytService(ExternalMovieHandlerImpl(), retrofit.create(NYTimesAPI::class.java))

}
