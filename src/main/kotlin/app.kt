import io.fritz2.dom.html.html
import io.fritz2.dom.mount


fun main() {
    html {
        p {
            text("Hello World!")
        }
    }.mount("target")
}