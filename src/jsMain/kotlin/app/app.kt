package app

import dev.fritz2.core.render
import dev.fritz2.core.storeOf
import kotlinx.coroutines.Job
import model.Framework
import model.name

fun main() {
    val frameworkStore = storeOf(Framework("fritz2"), job = Job())
    val name = frameworkStore.map(Framework.name())

    render {
        p {
            +"This site uses: "
            b { name.data.renderText() }
        }
    }
}