package feature.succecsscases

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import core.common.NavConfig
import core.common.navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishSuccessCasesBottomSheet(onDismissRequest: () -> Unit) {
    ModalBottomSheet(onDismissRequest = onDismissRequest, dragHandle = null) {
        Column {
            Text(
                text = "图文案例发布", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigation.push(NavConfig.SuccessImageCaseCreator)
                        onDismissRequest()
                    }
                    .padding(15.dp)
            )
            Divider()
            Text(text = "视频案例发布", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigation.push(NavConfig.SuccessVideoCaseCreator)
                        onDismissRequest()
                    }
                    .padding(15.dp))
            Divider()
            Text(text = "取消", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDismissRequest() }
                    .padding(15.dp))
        }
    }
}