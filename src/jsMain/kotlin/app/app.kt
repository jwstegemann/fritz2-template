package app

import dev.fritz2.core.render
import dev.fritz2.core.storeOf
import model.Framework
import model.name

fun main() {
    val frameworkStore = storeOf(Framework("fritz2"))
    val name = frameworkStore.map(Framework.name())

    render {
        p {
            +"This site uses: "
            b { name.data.renderText() }
        }
    }
}