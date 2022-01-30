package app

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.render
import model.Framework
import model.name

fun main() {
    val frameworkStore = storeOf(Framework("fritz2"))
    val name = frameworkStore.sub(Framework.name())

    render {
        p {
            +"This site uses: "
            b { name.data.renderText() }
        }
    }
}