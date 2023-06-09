import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RewardPointsScreen(modifier: Modifier = Modifier, component: RewardPointsComponent) {
    Scaffold(topBar = { NavigationTopBar(title="")}) { padding ->
        Column(Modifier.padding(padding)) {
            
        }
    }
}
