package feature.succecsscases.video_creator

import NavigationTopBar
import PostUIStateDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.design_system.SystemBar
import feature.succecsscases.creator.CaseContentEdit
import feature.succecsscases.image_creator.ImageUpload

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessCaseVideoCreatorScreen(component: SuccessCaseVideoCreatorComponent) {
    SystemBar(statusBar = MaterialTheme.colorScheme.secondaryContainer)
    Scaffold(
        topBar = {
            NavigationTopBar(
                title = "视频案例分享",
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(
                        onClick = { component.modelState.publishCase() },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                    ) {
                        Text(text = "发布")
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { padding ->
        Column(Modifier.padding(padding)) {
            CaseContentEdit(modifier = Modifier.weight(7f), component.modelState)
            Box(modifier = Modifier.weight(4f)) {
                VideoUpload(modifier = Modifier.fillMaxSize(), component)
            }
        }
        val postCaseUIState by component.modelState.postCaseUIStateFlow.collectAsState()
        PostUIStateDialog(postUIState = postCaseUIState)
    }
}