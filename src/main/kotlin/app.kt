import io.fritz2.dom.html.html
import io.fritz2.dom.mount


@ExperimentalCoroutinesApi
@FlowPreview
fun main() {
    html {
        p {
            +"Hello World!"
        }
    }.mount("target")
}