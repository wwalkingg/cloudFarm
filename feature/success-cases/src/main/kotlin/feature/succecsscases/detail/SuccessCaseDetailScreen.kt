package feature.succecsscases.detail

import NavigationTopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessCaseDetailScreen(modifier: Modifier = Modifier, component: SuccessCaseDetailComponent) {
    Scaffold(topBar = { NavigationTopBar(title = "") }) { padding ->
        Column(Modifier.padding(padding)) {

        }
    }
}