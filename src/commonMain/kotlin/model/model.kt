package model

import dev.fritz2.core.Lenses

// Put your model data classes in here to use it on js and jvm side

@Lenses
data class Framework(val name: String) {
    companion object
}
