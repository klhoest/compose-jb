package org.jetbrains.codeviewer.platform

import java.io.File
import java.lang.Exception
import java.util.*
import kotlin.collections.HashSet

object FavTagList {

    val favList = HashSet<String>()

    init {
        try {
            val favoritesFile = File("E:\\images\\dv\\galeryDl\\DeCoureM\\favTags.txt")
            val myReader = Scanner(favoritesFile)
            while (myReader.hasNextLine()) {
                val line = myReader.nextLine()
                val split = line.split('/')
                val favTag = split.last()
                favList.add(favTag)
            }
            myReader.close()
        } catch (e : Exception) {
            System.out.println("An error occurred.")
            e.printStackTrace()
        }
    }

    fun matchAuthAndFav(authTag : String): String? {
        return favList
            .find { authTag.contains(it, true) }
    }
}