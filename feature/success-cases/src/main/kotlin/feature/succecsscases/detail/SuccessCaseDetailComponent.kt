package feature.succecsscases.detail

import com.arkivanov.decompose.ComponentContext
import core.component_base.ModelState

class SuccessCaseDetailComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseDetailModelState()
}

internal class SuccessCaseDetailModelState : ModelState() {

}