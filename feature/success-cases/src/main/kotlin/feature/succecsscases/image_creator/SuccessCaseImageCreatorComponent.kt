package feature.succecsscases.image_creator

import com.arkivanov.decompose.ComponentContext
import feature.succecsscases.creator.CreatorModelState
import kotlinx.coroutines.launch

class SuccessCaseImageCreatorComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseImageCreatorModelState()
}

internal class SuccessCaseImageCreatorModelState : CreatorModelState() {
    fun uploadImage() {
        coroutineScope.launch {

        }
    }
}