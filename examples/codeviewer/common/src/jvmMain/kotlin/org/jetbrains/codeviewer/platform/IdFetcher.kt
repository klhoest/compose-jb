package org.jetbrains.codeviewer.platform

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.extractIt
import it.skrape.fetcher.skrape
import it.skrape.selects.eachAttribute
import it.skrape.selects.html5.a
import it.skrape.selects.html5.meta
import model.DaIdModel

object IdFetcher {

    /**
     * find content inside of <meta data-rh="true" property="da:appurl" content="DeviantArt://deviation/5170A574-242F-1A2F-CF03-2B26607A5DD6">
     */
    fun extract(webId : String) : DaIdModel {
        val extracted = skrape(HttpFetcher) {
            request {
                url = "https://www.deviantart.com/art/The-Mandalorian-posable-handmade-miniature-figure-$webId"
            }

            extractIt<DaIdModel> {
                it.httpStatusCode = this.responseStatus.code
                //it.httpStatusMessage = this.responseBody
                htmlDocument {
                    /*it.paragraph = meta {
                        findFirst {
                             attribute("property") toContain  "da:appurl"
                        }
                    }*/
                    it.meta = meta {
                        withAttribute = "property" to "da:appurl"
                        findFirst {
                            attribute("content")
                        }
                    }
                    it.allTagHref = a {
                        withAttribute = "class" to "Q-jc6"
                        findAll { eachAttribute("href") }
                    }
                    //it.paragraph = p { findFirst { text }}
                    //it.allLinks = a { findAll { eachHref }}
                }
            }
        }
        println(extracted)
        return extracted
    }
}