package feature.succecsscases.creator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import core.component_base.ModelState

internal open class CreatorModelState : ModelState() {
    var title by mutableStateOf("")
    var content by mutableStateOf("")
}