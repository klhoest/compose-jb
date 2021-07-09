import androidx.compose.desktop.Window
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.*
import tagfetcher.ImageProcess

fun main() = Window {

    var textState =  listOf("deviantart_885149433").asFlow()
        .map {
            try {
                if (it.startsWith("deviantart") == false)
                    throw IllegalArgumentException("the fileName does not start with deviantart")
                it.split('_')[1]
            } catch (e: Exception) {
                println("failure for ${it}, ${e.message}")
                null
            }
        }
        .filterNotNull()
        .map {
            try {
                ImageProcess.launch(it)
            } catch (e: Exception) {
                println("failure for ${it}, ${e.message}")
                null
            }
        }
        .filterNotNull()
        .onEach { list ->
            println(list)
            /*val a = list.map { authorTag ->
                WordApi.getSynonym(authorTag)
            }
            println(a)*/
        }
        .map { list ->
            var result = ""
            list.forEach { result = result + ", " + it}
            return@map result
        }
        .catch { e -> println("failure , ${e.message}") }
        .collectAsState("initial flow")
    val text by textState

    MaterialTheme {
        HelloContent(text) {}
    }
}

@Composable
fun HelloContent(name: String, onNameChange: () -> Unit) {
    Button(onClick = onNameChange) {
        Text(name)
    }
}

