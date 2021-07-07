object ImageProcess {

    fun launch(webId: String): List<String> {
        val daIdModel = IdFetcher.extract(webId)
        //val daIdModel = DaIdModel()
        return daIdModel.allTag
    }

}