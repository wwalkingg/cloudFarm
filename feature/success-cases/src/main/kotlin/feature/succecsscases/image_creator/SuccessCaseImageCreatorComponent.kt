package feature.succecsscases.image_creator

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateMapOf
import com.arkivanov.decompose.ComponentContext
import core.component_base.UploadUIState
import core.component_base.getFileName
import core.network.UploadState
import core.network.api.Apis
import core.network.api.uploadFile
import feature.succecsscases.creator.CreatorModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SuccessCaseImageCreatorComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseImageCreatorModelState()
}

internal class SuccessCaseImageCreatorModelState : CreatorModelState() {
    val imagesMap = mutableStateMapOf<Uri, MutableStateFlow<UploadUIState>>()
    fun uploadImage(context: Context, uri: Uri) {
        coroutineScope.launch {
            context.contentResolver.openInputStream(uri)!!.use { inputStream ->
                Apis.SuccessCases.uploadFile(
                    filename = uri.getFileName(context) ?: "undefined",
                    inputStream.readBytes()
                )
                    .onStart {
                        imagesMap[uri] = MutableStateFlow(UploadUIState.Idle)
                    }
                    .catch {
                        it.printStackTrace()
                        imagesMap[uri]?.emit(UploadUIState.Error(it.message ?: "unknown error"))
                    }
                    .collect { uploadState ->
                        println(uploadState)
                        when (uploadState) {
                            is UploadState.Complete -> {
                                imagesMap[uri]?.emit(UploadUIState.Success(uploadState.url))
                            }

                            UploadState.Idle -> {
                                imagesMap[uri]?.emit(UploadUIState.Idle)
                            }

                            is UploadState.InProgress -> {
                                imagesMap[uri]?.emit(UploadUIState.Progress(uploadState.progress.toFloat()))
                            }
                        }
                    }
            }
        }
    }
}