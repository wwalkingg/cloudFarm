package feature.succecsscases.detail

import LoadUIStateScaffold
import NavigationTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import core.common.Config
import video_player.VideoPlayer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SuccessCaseDetailScreen(modifier: Modifier = Modifier, component: SuccessCaseDetailComponent) {
    val loadDetailUIState by component.modelState.loadCaseDetailUIStateFlow.collectAsState()
    LoadUIStateScaffold(loadUIState = loadDetailUIState) { case ->
        Scaffold(
            topBar = { NavigationTopBar(title = case.title) },
            contentWindowInsets = WindowInsets(0.dp)
        ) { padding ->
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = case.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Divider()
                Text(
                    text = case.content,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                case.images.forEach { url ->
                    var isFullScreenLook by remember {
                        mutableStateOf(false)
                    }
                    AsyncImage(model = Config.baseImgUrl + url,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { isFullScreenLook = true }
                            .padding(horizontal = 10.dp)
                            .clip(MaterialTheme.shapes.small)
                            .fillMaxWidth()
                            .aspectRatio(1.2f)
                            .background(MaterialTheme.colorScheme.outlineVariant))
                    if (isFullScreenLook) {
                        Popup(
                            onDismissRequest = { isFullScreenLook = false },
                            properties = PopupProperties()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black)
                            ) {
                                val systemUiController = rememberSystemUiController()
                                DisposableEffect(Unit) {
                                    systemUiController.setStatusBarColor(Color.Black)
                                    systemUiController.setNavigationBarColor(Color.Black)
                                    onDispose {
                                        systemUiController.setStatusBarColor(Color.Transparent)
                                        systemUiController.setNavigationBarColor(Color.Transparent)
                                    }
                                }
                                AsyncImage(
                                    model = Config.baseImgUrl + url,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                IconButton(
                                    onClick = { isFullScreenLook = false },
                                    modifier = Modifier.padding(10.dp),
                                    colors = IconButtonDefaults.iconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surface
                                    )
                                ) {
                                    Icon(
                                        imageVector = androidx.compose.material.icons.Icons.Rounded.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
                if (case.video.isNotBlank()) {
                    VideoPlayer(
                        url = Config.baseImgUrl + case.video,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.2f)
                    )
                }
            }
        }
    }

}