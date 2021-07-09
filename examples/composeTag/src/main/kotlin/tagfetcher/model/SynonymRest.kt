package tagfetcher.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SynonymRest(
    @SerialName("word")
    val word: String,
    @SerialName("synonyms")
    val synonyms: List<String>
)