package model

import dev.fritz2.lenses.Lenses

// Put your model data classes in here to use it on js and jvm side

// will lead to an error -> no companion object defined
//@Lenses
//data class Framework(val name: String)

@Lenses
data class Framework(val name: String) {
    companion object {
        // will lead to an error -> name already defined
        //fun name(): String = "Hugo"
    }

}
