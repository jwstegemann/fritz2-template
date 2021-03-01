import dev.fritz2.dom.html.render

fun main() {
    render {
        p {
            +("Hello World!")
        }
    }
}