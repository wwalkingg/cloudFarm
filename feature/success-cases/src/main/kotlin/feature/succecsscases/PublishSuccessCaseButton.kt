package feature.succecsscases

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun PublishSuccessCaseButton(component: SuccessCaseComponent) {
    ExtendedFloatingActionButton(onClick = { component.modelState.isPublishTypeSBottomSheetVisible = true }) {
        val degree by animateFloatAsState(targetValue = if (component.modelState.isPublishTypeSBottomSheetVisible) 45f else 0f)
        Icon(imageVector = Icons.Rounded.Add, contentDescription = null, modifier = Modifier.rotate(degree))
    }
}
