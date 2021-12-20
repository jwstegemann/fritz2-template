import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.Transition
import dev.fritz2.dom.html.render
import dev.fritz2.dom.html.transition
import dev.fritz2.identification.Id


val fade = Transition(
    "transition-all duration-1000",
    "opacity-0",
    "opacity-100",
    "transition-all ease-out duration-1000",
    "opacity-100",
    "opacity-0"
)

//FIXME: use from WebComponents?


//class Fragment(override val job: Job, override val scope: Scope, private val apply: WithDomNode<Element>.() -> Unit) :
//    RenderContext, WithDomNode<DocumentFragment> {
//    override val domNode: DocumentFragment = document.createDocumentFragment()
//
//    override fun <E : Node, T : WithDomNode<E>> register(element: T, content: (T) -> Unit): T {
//        if (element.domNode is Element) this.apply.invoke(element.unsafeCast<WithDomNode<Element>>())
//        content(element)
//        domNode.appendChild(element.domNode)
//        return element
//    }
//}
//
//fun RenderContext.fragment(
//    scopeContext: (ScopeContext.() -> Unit) = {},
//    content: RenderContext.() -> Unit,
//    apply: WithDomNode<Element>.() -> Unit = {},
//): Fragment {
//    //FIXME: replace by evalScope
//    val s = ScopeContext(this.scope).apply(scopeContext).scope
//    return register(Fragment(job, s, apply), content)
//}


fun main() {
    val visible = storeOf(true)

    val list = storeOf(listOf(Id.next(), Id.next(), Id.next()))
    val addItem = list.handle { it + Id.next() }
    val addMany = list.handle { it + Id.next() + Id.next() + Id.next() }
    val removeMany = list.handle { it.take(1) }
    val removeItem = list.handle { list, value: String -> list - value }

//    MainScope().launch {
//        for (i in 1..10000) {
//            console.log("$i")
//            delay(150)
//            if (i % 2 == 0) {
//                addMany()
//            } else {
//                removeMany()
//            }
//        }
//    }

    render {
        div {
            button("p-4 border") {
                +"add"
                clicks handledBy addItem
            }
            button("p-4 border") {
                +"add many"
                clicks handledBy addMany
            }
            button("p-4 border") {
                +"shuffle"
                clicks handledBy list.handle { it.shuffled() }
            }
            button("p-4 border") {
                +"remove many"
                clicks handledBy removeMany
            }

            list.data.renderEach({ it }) { item ->
                div { //FIXME: get rid of this?
                    div(id = "myDiv") {
                        inlineStyle("margin-top: 10px; width: 200px; height: 50px; background-color: lightblue;")
                        transition(fade)
                        +item
                        clicks.map { item } handledBy removeItem
                    }
                }
            }
        }

        div {
            inlineStyle("margin-top: 20px;")
            button("p-4 border") {
                +"toggle"
                clicks handledBy visible.handle { !it }
            }
            visible.data.render {
                if (it) {
                    div {
                        div(id = "myDiv") {
                            inlineStyle("margin-top: 10px; width: 200px; height: 200px; background-color: red;")
                            transition(fade)
                        }

                        div(id = "myDiv2") {
                            inlineStyle("margin-top: 10px; width: 200px; height: 200px; background-color: red;")
                            transition(fade)
                        }

                        div(id = "myDiv3") {
                            inlineStyle("margin-top: 10px; width: 200px; height: 200px; background-color: red;")
                            transition(fade)
                        }
                    }
                }
            }
        }
    }
}