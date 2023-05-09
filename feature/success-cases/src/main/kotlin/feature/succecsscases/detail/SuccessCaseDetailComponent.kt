package feature.succecsscases.detail

import com.arkivanov.decompose.ComponentContext
import com.example.android.core.model.SuccessCase
import core.component_base.LoadUIState
import core.component_base.ModelState
import core.network.api.Apis
import core.network.api.getCaseDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SuccessCaseDetailComponent(componentContext: ComponentContext, val id: Int) :
    ComponentContext by componentContext {
    internal val modelState = SuccessCaseDetailModelState(id)
}

internal class SuccessCaseDetailModelState(val id: Int) : ModelState() {
    private val _loadCaseDetailUIStateFlow = MutableStateFlow<LoadUIState<SuccessCase>>(LoadUIState.Loading)
    val loadCaseDetailUIStateFlow = _loadCaseDetailUIStateFlow.asStateFlow()
    fun loadDetail() {
        coroutineScope.launch {
            Apis.SuccessCases.getCaseDetail(id)
                .onStart { _loadCaseDetailUIStateFlow.emit(LoadUIState.Loading) }
                .catch {
                    it.printStackTrace()
                    _loadCaseDetailUIStateFlow.emit(LoadUIState.Error(it))
                }
                .collect {
                    _loadCaseDetailUIStateFlow.emit(LoadUIState.Success(it))
                }
        }
    }

    init {
        loadDetail()
    }
}