package feature.succecsscases

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android.core.model.SuccessCase

@Composable
fun HotTopPic(modifier: Modifier = Modifier, cases: List<SuccessCase>, onTopicClick: (SuccessCase) -> Unit) {
    val mergeModifier = modifier.fillMaxWidth()
    Surface(
        modifier = mergeModifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier) {
            Row(
                modifier = Modifier
                    .padding(10.dp, 8.dp)
                    .drawWithContent {
                        drawIntoCanvas { canvas ->
                            canvas.saveLayer(Rect(Offset.Zero, size), Paint())
                            drawContent()
                            drawRect(
                                Brush.horizontalGradient(listOf(Color.Red, Color(0XFF2C5807))),
                                blendMode = BlendMode.SrcIn
                            )
                            canvas.restore()
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Flag, contentDescription = null)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "热门话题",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                cases.take(5).forEach { case ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onTopicClick(case) }
                            .padding(10.dp, 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Tag,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .size(20.dp)
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = case.title, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
