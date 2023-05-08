package feature.succecsscases.creator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
internal fun CaseContentEdit(modifier: Modifier = Modifier, creatorModelState: CreatorModelState) {
    Surface(modifier = modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.secondaryContainer) {
        Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
            val titleStyle = MaterialTheme.typography.titleLarge
            BasicTextField(
                value = creatorModelState.title,
                onValueChange = { creatorModelState.title = it },
                textStyle = titleStyle,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .weight(2f),
                decorationBox = {
                    if (creatorModelState.title.isEmpty()) {
                        Text(text = "请输入标题", style = titleStyle, modifier = Modifier.alpha(.8f))
                    }
                    it()
                },
            )
            Divider()
            val contentStyle = MaterialTheme.typography.bodyLarge
            BasicTextField(
                value = creatorModelState.content,
                onValueChange = { creatorModelState.content = it },
                textStyle = contentStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .weight(7f),
                decorationBox = {
                    if (creatorModelState.title.isEmpty()) {
                        Text(text = "请输入内容", style = contentStyle, modifier = Modifier.alpha(.8f))
                    }
                    it()
                },
            )
        }
    }
}