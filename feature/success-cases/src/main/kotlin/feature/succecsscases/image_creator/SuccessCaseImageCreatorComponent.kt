package feature.succecsscases.image_creator

import com.arkivanov.decompose.ComponentContext
import core.component_base.ModelState
import feature.succecsscases.creator.CreatorModelState

class SuccessCaseImageCreatorComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseImageCreatorModelState()
}

internal class SuccessCaseImageCreatorModelState : CreatorModelState() {

}