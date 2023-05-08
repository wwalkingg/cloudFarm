package feature.succecsscases.creator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
internal fun CaseContentEdit(modifier: Modifier = Modifier, creatorModelState: CreatorModelState) {
    Surface(modifier = modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.secondaryContainer) {
        Column(modifier = Modifier.padding(20.dp)) {
            val titleStyle = MaterialTheme.typography.displaySmall
            val titleHeight = with(LocalDensity.current){
                titleStyle.lineHeight.toDp()
            }
            BasicTextField(
                value = creatorModelState.title,
                onValueChange = { creatorModelState.title = it },
                textStyle = titleStyle,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(titleHeight)
            )
            Divider()
            val contentStyle = MaterialTheme.typography.bodyLarge
            val contentHeight = with(LocalDensity.current){
                contentStyle.lineHeight.toDp()
            }
            BasicTextField(
                value = creatorModelState.content,
                onValueChange = { creatorModelState.content = it },
                textStyle = contentStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .heightIn(contentHeight,contentHeight * 5)
            )
        }
    }
}