package feature.succecsscases.image_creator

import NavigationTopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            CaseContentEdit(modifier = Modifier.fillMaxWidth(), component.modelState)
        }
    }
}