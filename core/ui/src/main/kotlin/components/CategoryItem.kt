package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android.core.model.Category
import core.common.Config

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: () -> Unit,
    shape: Shape = MaterialTheme.shapes.medium,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    contentPadding: PaddingValues = PaddingValues(2.dp),
) {
    Column(
        modifier
            .clip(shape)
            .clickable { onClick() }
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .weight(1f)
                .clip(CircleShape)
                .background(Color.LightGray)
                .layout { measurable, constraints ->
                    val min = Integer.min(constraints.maxWidth, constraints.maxHeight)
                    val placeable =
                        measurable.measure(constraints.copy(maxWidth = min, maxHeight = min))
                    layout(placeable.width, placeable.height) {
                        placeable.placeRelative(0, 0)
                    }
                }, model = Config.baseImgUrl+category.categoryImg, contentDescription = null, contentScale = ContentScale.Crop
        )
        Text(text = category.name, style = textStyle)
    }
}