package feature.succecsscases.my

import LoadUIStateScaffold
import NavigationTopBar
import PostUIStateDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import core.common.NavConfig
import core.common.navigation
import feature.succecsscases.SuccessCaseItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCaseScreen(component: MyCaseComponent) {
    Scaffold(topBar = { NavigationTopBar(title = "我的分享") }) { padding ->
        val loadMyCaseUIState by component.modelState.loadMyCaseUIStateFlow.collectAsState()
        LoadUIStateScaffold(loadUIState = loadMyCaseUIState, modifier = Modifier.padding(padding)) { cases ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = cases.dropWhile { it.id in component.modelState.deletedList }) { case ->
                    Column(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        var isDeleteDialog by remember {
                            mutableStateOf(false)
                        }
                        SuccessCaseItem(
                            modifier = Modifier.fillMaxWidth(),
                            case,
                            onClick = { navigation.push(NavConfig.SuccessCaseDetail(case.id)) })
                        Divider()
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            IconButton(onClick = { isDeleteDialog = true }) {
                                Icon(imageVector = Icons.Rounded.Delete, contentDescription = null)
                            }
                        }
                        val deleteCaseUIStateFlow by component.modelState.deleteCaseUIStateFlow.collectAsState()
                        if (isDeleteDialog) {
                            AlertDialog(
                                onDismissRequest = { isDeleteDialog = false },
                                text = { Text(text = "确定删除") },
                                dismissButton = {
                                    TextButton(onClick = {
                                        isDeleteDialog = false
                                    }) {
                                        Text(text = "取消")
                                    }
                                },
                                confirmButton = {
                                    TextButton(onClick = {
                                        isDeleteDialog = false
                                        component.modelState.deleteCase(case.id)
                                    }) {
                                        Text(text = "删除")
                                    }
                                }
                            )
                        }
                        PostUIStateDialog(postUIState = deleteCaseUIStateFlow)
                    }
                }
            }
        }
    }
}
