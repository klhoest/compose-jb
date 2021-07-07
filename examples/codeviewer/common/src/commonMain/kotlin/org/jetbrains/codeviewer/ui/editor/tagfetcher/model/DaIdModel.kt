package model

data class DaIdModel(
    var httpStatusCode: Int = -1,
    var meta: String = "DeviantArt://deviation/5170A574-242F-1A2F-CF03-2B26607A5DD6",
    var allTagHref: List<String> = emptyList()
) {
    val id: String
        get() = meta.split("deviation/").last()

    val allTag: List<String>
        get() =
            allTagHref
                .map { it.removePrefix("https://www.deviantart.com/tag/")}
}