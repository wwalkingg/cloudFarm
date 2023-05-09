package feature.succecsscases

import SmallLoadUIStateScaffold
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import com.example.core.design_system.SystemBar
import core.common.NavConfig
import core.common.navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessCaseScreen(component: SuccessCaseComponent) {
    SystemBar(MaterialTheme.colorScheme.background)
    Scaffold(
        floatingActionButton = { PublishSuccessCaseButton(component) },
        topBar = { TopAppBar(title = { Text(text = "成功案例分享") }, windowInsets = WindowInsets(0.dp)) },
        contentWindowInsets = WindowInsets(0.dp)
    ) {
        Column(modifier = Modifier.padding(it)) {
            val loadAllCasesUIState by component.modelState.loadAllCaseUIStateFlow.collectAsState()
            SmallLoadUIStateScaffold(loadUIState = loadAllCasesUIState) { cases ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        HotTopPic(modifier = Modifier, cases) { case ->
                            navigation.push(NavConfig.SuccessCaseDetail(case.id))
                        }
                    }
                    items(items = cases) { case ->
                        SuccessCaseItem(
                            modifier = Modifier.fillMaxWidth(),
                            case,
                            onClick = { navigation.push(NavConfig.SuccessCaseDetail(case.id)) })
                    }
                }
            }
        }
    }
    if (component.modelState.isPublishTypeSBottomSheetVisible) {
        PublishSuccessCasesBottomSheet(
            onDismissRequest = {
                component.modelState.isPublishTypeSBottomSheetVisible = false
            }
        )
    }
}

