import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import core.component_base.PostUIState

@Composable
fun PostUIStateDialog(
    modifier: Modifier = Modifier,
    postUIState: PostUIState,
    dialogStayTime: Long = 1300L,
    loading: @Composable () -> Unit = {
        CircularProgressIndicator()
    },
    error: @Composable (throwable: Throwable) -> Unit = {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(imageVector = Icons.Default.Error, contentDescription = null, tint = Color.Red)
            Text(text = it.message ?: "未知错误", style = MaterialTheme.typography.labelMedium)
        }
    },
    success: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xff004c08))
    }
) {
    when (postUIState) {
        is PostUIState.Error -> {
            DialogContent(modifier) {
                error(postUIState.error)
            }
        }

        PostUIState.Loading -> {
            DialogContent(modifier) {
                loading()
            }
        }

        PostUIState.None -> {}
        PostUIState.Success -> {
            DialogContent(modifier) {
                success()
            }
        }
    }
}

@Composable
fun DialogContent(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = {}) {
        Box(
            modifier = modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth(.7f)
                .aspectRatio(1f)
                .background(Color.White)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}