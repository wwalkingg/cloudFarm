package feature.succecsscases.creator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip


@Composable
internal fun UploadProgress(modifier: Modifier = Modifier, progress: Float) {
    Box(modifier = modifier.clip(MaterialTheme.shapes.small), contentAlignment = Alignment.BottomCenter){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(progress)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(.8f))
        )
    }
}