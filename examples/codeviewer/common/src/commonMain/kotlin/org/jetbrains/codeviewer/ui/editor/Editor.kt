package org.jetbrains.codeviewer.ui.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.codeviewer.platform.File
import org.jetbrains.codeviewer.util.EmptyTextLines
import org.jetbrains.codeviewer.util.SingleSelection
import org.jetbrains.codeviewer.util.TextLines

class Editor(
    val fileName: String,
    val lines: (backgroundScope: CoroutineScope) -> Lines,
) {
    var close: (() -> Unit)? = null
    lateinit var selection: SingleSelection

    val isActive: Boolean
        get() = selection.selected === this

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

fun Editor(file: File) = Editor(
    fileName = file.name
) { backgroundScope ->
    val textLines: TextLines = try {
        file.readLines(backgroundScope)
    } catch (e: Throwable) {
        e.printStackTrace()
        EmptyTextLines
    }
    val isCode = file.name.endsWith(".kt", ignoreCase = true)
    val textSize = textLines.size
    val resultList = StringBuilder("")
    for (i in 0..textSize) {
        val authorTag = textLines.get(i).trim('\n')
        file.matchFavAndAuthor(authorTag)?.let {
            resultList.append("$it, ")
        }
    }

    fun content(index: Int): Editor.Content {
        // TODO: maybe use another symbols, i.e. \u2800 or \u00a0.
        val state = mutableStateOf(if (resultList.isEmpty()) " " else resultList.toString())
        return Editor.Content(state, isCode)
    }

    object : Editor.Lines {
        override val size get() = 3

        override fun get(index: Int) = Editor.Line(
            number = index + 1,
            content = content(index)
        )
    }


}
