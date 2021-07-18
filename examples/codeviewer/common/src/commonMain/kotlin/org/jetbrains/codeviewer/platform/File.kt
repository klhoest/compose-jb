package org.jetbrains.codeviewer.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.jetbrains.codeviewer.util.TextLines

expect val HomeFolder: File

interface File {
    val name: String
    val isDirectory: Boolean
    val children: List<File>
    val hasChildren: Boolean

    fun readLines(scope: CoroutineScope): TextLines
    fun readLines(): Flow<String>
    fun process(scope: CoroutineScope): TextLines
    fun matchFavAndAuthor(authorTag: String): String?
}