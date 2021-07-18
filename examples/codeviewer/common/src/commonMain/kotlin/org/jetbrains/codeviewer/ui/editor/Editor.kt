package org.jetbrains.codeviewer.ui.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.jetbrains.codeviewer.platform.File
import org.jetbrains.codeviewer.util.EmptyTextLines
import org.jetbrains.codeviewer.util.SingleSelection
import org.jetbrains.codeviewer.util.TextLines

class Editor(
    val file : File
) {

    var close: (() -> Unit)? = null
    lateinit var selection: SingleSelection

    val isActive: Boolean
        get() = selection.selected === this

    @Suppress("SimplifyBooleanWithConstants")
    fun launch() = flow<List<String>> {
        //_lineFlow.emit("start")
        val textLines = file.readLines().toList()
        /*val textLines: TextLines = try {
            file.readLines()
        } catch (e: Throwable) {
            e.printStackTrace()
            EmptyTextLines
        }*/
        val isCode = file.name.endsWith(".kt", ignoreCase = true)
        val textSize = textLines.size
        val resultList = mutableListOf<String>()
        for (i in 0 until textSize) {
            val authorTag = textLines.get(i).trim('\n')
            file.matchFavAndAuthor(authorTag)?.let { favTag ->
                if(resultList.contains(favTag) == false)
                    resultList.add(favTag)
            }
        }
        emit(resultList)
    }

    fun activate() {
        selection.selected = this
    }

    class Line(val number: Int, val content: Content)

    interface Lines {
        val lineNumberDigitCount: Int get() = size.toString().length
        val size: Int
        operator fun get(index: Int): Line
    }

    class Content(val value: State<String>, val isCode: Boolean)
}
