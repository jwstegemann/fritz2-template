import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.render
import dev.fritz2.lenses.Lenses
import kotlinx.coroutines.flow.map
import model.Framework
import model.name

// Does not work! Lense is not recognized...
/*
@Lenses
data class Framework(val name: String) {
    companion object
}

 */

fun main() {
    val frameworkStore = storeOf(Framework("fritz2"))
    val nameSub = Framework.name

    render {
        p {
            +"This site uses: "
            b { frameworkStore.data.map { it.name }.asText() }
        }
    }
}