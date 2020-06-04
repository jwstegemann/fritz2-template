import io.fritz2.dom.html.render
import io.fritz2.dom.mount


fun main() {
    //hugo()
    render {
        p {
            text("Hello World!")
        }
    }.mount("target")
}