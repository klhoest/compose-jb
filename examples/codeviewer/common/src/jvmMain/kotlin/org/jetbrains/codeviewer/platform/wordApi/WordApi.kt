package wordApi

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import model.SynonymRest

object WordApi {
    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getSynonym(authorTag : String): SynonymRest {
        try {
            val synonymRest: SynonymRest = client.get("https://wordsapiv1.p.rapidapi.com/words/$authorTag/synonyms") {
                headers {
                    append("x-rapidapi-key", "d88c1cf9admshbd851bf2c009ed3p1091e8jsnc8c671f3366e")
                    append("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                    append(HttpHeaders.Accept, "application/json")
                }
            }
            return synonymRest
        } catch (e: Exception) {
            println("could not get synonym of ${authorTag}, ${e.message}")
            return SynonymRest(authorTag, listOf())
        }
    }


}