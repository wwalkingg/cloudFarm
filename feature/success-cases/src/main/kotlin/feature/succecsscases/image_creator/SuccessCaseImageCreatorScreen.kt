package feature.succecsscases.image_creator

import NavigationTopBar
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.design_system.SystemBar
import feature.succecsscases.creator.CaseContentEdit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessCaseImageCreatorScreen(component: SuccessCaseImageCreatorComponent) {
    SystemBar(statusBar = MaterialTheme.colorScheme.secondaryContainer)
    Scaffold(
        topBar = {
            NavigationTopBar(
                title = "图文案例分享",
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { padding ->
        Column(Modifier.padding(padding)) {
            CaseContentEdit(modifier = Modifier.weight(7f), component.modelState)
            Box(modifier = Modifier.weight(4f)){
                ImageUpload(modifier = Modifier.fillMaxSize(),component)
            }
        }
    }
}