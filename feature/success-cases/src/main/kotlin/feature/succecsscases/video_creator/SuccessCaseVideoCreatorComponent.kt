package feature.succecsscases.video_creator

import com.arkivanov.decompose.ComponentContext
import core.component_base.ModelState
import feature.succecsscases.creator.CreatorModelState

class SuccessCaseVideoCreatorComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseCreatorModelState()
}

internal class SuccessCaseCreatorModelState : CreatorModelState() {

}