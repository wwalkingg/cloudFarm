package feature.succecsscases.my

import androidx.compose.runtime.mutableStateListOf
import com.arkivanov.decompose.ComponentContext
import com.example.android.core.model.SuccessCase
import core.component_base.LoadUIState
import core.component_base.ModelState
import core.component_base.PostUIState
import core.network.api.Apis
import core.network.api.deleteMyCase
import core.network.api.getMyCases
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyCaseComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = MyCaseModelState()
}

internal class MyCaseModelState : ModelState() {

    val deletedList = mutableStateListOf<Int>()

    private val _loadMyCaseUIStateFlow = MutableStateFlow<LoadUIState<List<SuccessCase>>>(LoadUIState.Loading)
    val loadMyCaseUIStateFlow = _loadMyCaseUIStateFlow.asStateFlow()
    fun loadMyCase() {
        deletedList.clear()
        coroutineScope.launch {
            Apis.SuccessCases.getMyCases()
                .onStart { _loadMyCaseUIStateFlow.emit(LoadUIState.Loading) }
                .catch {
                    it.printStackTrace()
                    _loadMyCaseUIStateFlow.emit(LoadUIState.Error(it))
                }
                .collect {
                    _loadMyCaseUIStateFlow.emit(LoadUIState.Success(it))
                }
        }
    }

    private val _deleteCaseUIStateFlow = MutableStateFlow<PostUIState>(PostUIState.None)
    val deleteCaseUIStateFlow = _deleteCaseUIStateFlow.asStateFlow()

    fun deleteCase(id: Int) {
        coroutineScope.launch {
            Apis.SuccessCases.deleteMyCase(id)
                .onStart { _deleteCaseUIStateFlow.emit(PostUIState.Loading) }
                .catch {
                    it.printStackTrace()
                    _deleteCaseUIStateFlow.emit(PostUIState.Error(it))
                    delay(1000L)
                    _deleteCaseUIStateFlow.emit(PostUIState.None)
                }
                .collect {
                    _deleteCaseUIStateFlow.emit(PostUIState.Success)
                    delay(1000L)
                    _deleteCaseUIStateFlow.emit(PostUIState.None)
                    deletedList.add(id)
                }
        }
    }

    init {
        loadMyCase()
    }
}