package core.component_base

import androidx.compose.runtime.Stable
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Stable
abstract class ModelState : InstanceKeeper.Instance {
    val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onDestroy() {
        coroutineScope.cancel()
    }
}