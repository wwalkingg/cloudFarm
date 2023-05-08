package feature.succecsscases

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import com.example.android.core.model.SuccessCase
import core.component_base.LoadUIState
import core.component_base.ModelState
import core.network.api.Apis
import core.network.api.getAll
import core.network.api.getMyCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@Stable
class SuccessCaseComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseModelState()
}

internal class SuccessCaseModelState : ModelState() {
    var isPublishTypeSBottomSheetVisible by mutableStateOf(false)

    private val _loadAllCaseUIStateFlow = MutableStateFlow<LoadUIState<List<SuccessCase>>>(LoadUIState.Loading)
    val loadAllCaseUIStateFlow = _loadAllCaseUIStateFlow.asStateFlow()

    fun loadAllCase() {
        coroutineScope.launch {
            Apis.SuccessCases.getAll()
                .onStart { _loadAllCaseUIStateFlow.emit(LoadUIState.Loading) }
                .catch {
                    it.printStackTrace()
                    _loadAllCaseUIStateFlow.emit(LoadUIState.Error(it))
                }
                .collect {
                    _loadAllCaseUIStateFlow.emit(LoadUIState.Success(it))
                }
        }
    }

    init {
        loadAllCase()
    }
}