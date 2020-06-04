import io.fritz2.dom.html.render
import io.fritz2.dom.mount


fun main() {
    render {
        p {
            text("Hello World!")
        }
    }.mount("target")
}