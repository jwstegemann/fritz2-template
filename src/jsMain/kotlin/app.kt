import dev.fritz2.binding.storeOf
import dev.fritz2.dom.DomLifecycle
import dev.fritz2.dom.DomLifecycleHandler
import dev.fritz2.dom.html.render
import dev.fritz2.dom.mountPoint
import kotlinx.coroutines.asDeferred
import org.w3c.dom.Node
import kotlin.js.Promise

class Transition(
    val enter: String,
    val enterStart: String,
    val enterEnd: String,
    val leave: String,
    val leaveStart: String,
    val leaveEnd: String
)

val fade = Transition(
    "transition-all duration-1000",
    "opacity-0",
    "opacity-100",
    "transition-all ease-out duration-1000",
    "opacity-100",
    "opacity-0"
)

//FIXME: use from WebComponents?
@JsName("Function")
private external fun <T> nativeFunction(vararg params: String, block: String): T

val animationDone = nativeFunction<(Node) -> Promise<Unit>>(
    "_node", block = """
         return Promise.all(
           _node.getAnimations().map(
             function(animation) {
               return animation.finished
             }
           )
         )
    """.trimIndent()
)

val leaveTransition: DomLifecycleHandler = { tag, payload ->
    val transition = payload.unsafeCast<Transition?>()
    if (transition != null) {
        val classes = tag.domNode.getAttribute("class").orEmpty()
        tag.domNode.setAttribute("class", "$classes ${transition.leaveStart}")
        tag.domNode.setAttribute("class", "$classes ${transition.leave} ${transition.leaveEnd}")
    }
    animationDone(tag.domNode).asDeferred()
}

val enterTransition: DomLifecycleHandler = { tag, payload ->
    val transition = payload.unsafeCast<Transition?>()
    if (transition != null) {
        val classes = tag.domNode.getAttribute("class").orEmpty() //baseClass
        tag.domNode.setAttribute("class", "$classes ${transition.enterStart}")
        kotlinx.browser.window.requestAnimationFrame {
            tag.domNode.setAttribute("class", "$classes ${transition.enter} ${transition.enterEnd}")
            animationDone(tag.domNode).then {
                tag.domNode.setAttribute("class", classes)
            }
        }
    }
    animationDone(tag.domNode).asDeferred() // Accept null
}

fun main() {
    val visible = storeOf(true)

    render {
        div {
            button("p-4 border") {
                +"toggle"
                clicks handledBy visible.handle { !it }
            }
            visible.data.render {
                if (it) {
                    div(id = "myDiv") {
                        inlineStyle("margin-top: 10px; width: 200px; height: 200px; background-color: red;")
                    }.apply {
                        val target = this
                        mountPoint()?.apply {
                            console.log("mount-point found")
                            beforeUnmount(DomLifecycle(target, leaveTransition, fade))
                            afterMount(DomLifecycle(target, enterTransition, fade))
                        }
                    }

                    div(id = "myDiv2") {
                        inlineStyle("margin-top: 10px; width: 200px; height: 200px; background-color: red;")
                    }.apply {
                        val target = this
                        mountPoint()?.apply {
                            console.log("mount-point found")
                            beforeUnmount(DomLifecycle(target, leaveTransition, fade))
                            afterMount(DomLifecycle(target, enterTransition, fade))
                        }
                    }

                    div(id = "myDiv3") {
                        inlineStyle("margin-top: 10px; width: 200px; height: 200px; background-color: red;")
                    }.apply {
                        val target = this
                        mountPoint()?.apply {
                            console.log("mount-point found")
                            beforeUnmount(DomLifecycle(target, leaveTransition, fade))
                            afterMount(DomLifecycle(target, enterTransition, fade))
                        }
                    }

                }
            }
        }
    }
}