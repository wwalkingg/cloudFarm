package feature.succecsscases.image_creator

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddAPhoto
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import feature.succecsscases.creator.UploadProgress

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageUpload(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val imageData = remember { mutableStateListOf<Uri>() }


    Surface(modifier.padding(start = 10.dp)) {
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val itemModifier = Modifier
                .fillMaxWidth(99 / 300f)
                .aspectRatio(1f)
                .padding(bottom = 10.dp, end = 10.dp)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primaryContainer)

            imageData.forEach { imageUri ->
                Box(modifier = Modifier) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = null,
                        modifier = itemModifier.border(
                            1.dp,
                            MaterialTheme.colorScheme.outlineVariant,
                            MaterialTheme.shapes.small
                        ),
                        contentScale = ContentScale.Crop
                    )
                    UploadProgress(
                        modifier = Modifier
                            .fillMaxWidth(99 / 300f)
                            .aspectRatio(1f)
                            .padding(bottom = 10.dp, end = 10.dp),
                        progress = 0.5f
                    )
                }
            }
            // add image
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    imageData.add(it!!)
                }
            AddImage(
                modifier = itemModifier
            ) {
                launcher.launch("image/*")
            }
        }
    }
}

@Composable
private fun AddImage(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }) {
            Icon(
                Icons.Rounded.AddAPhoto,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(.8f),
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
