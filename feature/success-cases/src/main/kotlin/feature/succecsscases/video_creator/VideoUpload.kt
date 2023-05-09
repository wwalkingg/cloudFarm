package feature.succecsscases.video_creator

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VideoFile
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import core.component_base.UploadUIState
import core.component_base.getFileName
import feature.succecsscases.creator.UploadError
import feature.succecsscases.creator.UploadProgress
import feature.succecsscases.creator.UploadSuccess

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VideoUpload(modifier: Modifier = Modifier, component: SuccessCaseVideoCreatorComponent) {
    val context = LocalContext.current
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
            component.modelState.videoMap.forEach { (imageUri, uploadStateFlow) ->
                Box(modifier = Modifier) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(99 / 300f)
                            .aspectRatio(1f)
                            .padding(bottom = 10.dp, end = 10.dp)
                            .clip(MaterialTheme.shapes.small)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.outlineVariant,
                                MaterialTheme.shapes.small
                            )
                    ) {
                        Text(
                            text = imageUri.getFileName(context).toString(),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    val uploadState by uploadStateFlow.collectAsState()
                    when (uploadState) {
                        is UploadUIState.Error -> {
                            UploadError(
                                modifier = Modifier
                                    .fillMaxWidth(99 / 300f)
                                    .aspectRatio(1f)
                                    .padding(bottom = 10.dp, end = 10.dp)
                            ) {
                                component.modelState.removeUri(uri = imageUri)
                            }
                        }

                        UploadUIState.Idle -> {
                            UploadProgress(
                                modifier = Modifier
                                    .fillMaxWidth(99 / 300f)
                                    .aspectRatio(1f)
                                    .padding(bottom = 10.dp, end = 10.dp),
                                progress = 0f
                            )
                        }

                        is UploadUIState.Progress -> {
                            val progress by animateFloatAsState(targetValue = (uploadState as UploadUIState.Progress).percent)
                            UploadProgress(
                                modifier = Modifier
                                    .fillMaxWidth(99 / 300f)
                                    .aspectRatio(1f)
                                    .padding(bottom = 10.dp, end = 10.dp),
                                progress = progress
                            )
                        }

                        is UploadUIState.Success -> {
                            UploadSuccess(
                                modifier = Modifier
                                    .fillMaxWidth(99 / 300f)
                                    .aspectRatio(1f)
                                    .padding(bottom = 10.dp, end = 10.dp)
                            ) {
                                component.modelState.removeUri(uri = imageUri)
                            }
                        }
                    }
                }
            }
            // add image
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
                    component.modelState.uploadImage(context, it!!)
                }
            AddVideo(
                modifier = itemModifier
            ) {
                launcher.launch("video/*")
            }
        }
    }
}

@Composable
private fun AddVideo(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() }) {
            Icon(
                Icons.Rounded.VideoFile,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(.8f),
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Center)
            )
        }
    }
}
