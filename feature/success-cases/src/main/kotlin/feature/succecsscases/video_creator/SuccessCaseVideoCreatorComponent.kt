package feature.succecsscases.video_creator

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateMapOf
import com.arkivanov.decompose.ComponentContext
import com.example.android.core.model.SuccessCase
import core.component_base.PostUIState
import core.component_base.UploadUIState
import core.component_base.getFileName
import core.network.UploadState
import core.network.api.Apis
import core.network.api.publishCase
import core.network.api.uploadFile
import feature.succecsscases.creator.CreatorModelState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class SuccessCaseVideoCreatorComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    internal val modelState = SuccessCaseCreatorModelState()
}

internal class SuccessCaseCreatorModelState : CreatorModelState() {
    val videoMap = mutableStateMapOf<Uri, MutableStateFlow<UploadUIState>>()

    private val _postCaseUIStateFlow = MutableStateFlow<PostUIState>(PostUIState.None)
    val postCaseUIStateFlow = _postCaseUIStateFlow.asStateFlow()
    fun publishCase() {
        coroutineScope.launch {
            // check state
            if (!videoMap.values.all { it.value is UploadUIState.Success }) {
                _postCaseUIStateFlow.emit(PostUIState.Error(Exception("稍等文件未上传完成")))
                println(videoMap.values.map { it.value })
                delay(1500L)
                _postCaseUIStateFlow.emit(PostUIState.None)
            }else{
                val case = SuccessCase(
                    id = (0..Int.MAX_VALUE).random(),
                    customerId = 0,
                    images = videoMap.values.map {
                        if (it.value is UploadUIState.Success) {
                            (it.value as UploadUIState.Success).url
                        } else ""
                    },
                    video = "",
                    title = title,
                    content = content,
                    createTime = Clock.System.now().toString(),
                    topic = listOf()
                )
                Apis.SuccessCases.publishCase(case)
                    .onStart {
                        _postCaseUIStateFlow.emit(PostUIState.Loading)
                    }
                    .catch {
                        it.printStackTrace()
                        _postCaseUIStateFlow.emit(PostUIState.Error(it))
                        delay(1500L)
                        _postCaseUIStateFlow.emit(PostUIState.None)
                    }
                    .collect {
                        _postCaseUIStateFlow.emit(PostUIState.Success)
                        delay(1500L)
                        _postCaseUIStateFlow.emit(PostUIState.None)
                    }
            }
        }
    }

    fun uploadImage(context: Context, uri: Uri) {
        coroutineScope.launch {
            context.contentResolver.openInputStream(uri)!!.use { inputStream ->
                Apis.SuccessCases.uploadFile(
                    filename = uri.getFileName(context) ?: "undefined",
                    inputStream.readBytes()
                )
                    .onStart {
                        videoMap.clear()
                        videoMap[uri] = MutableStateFlow(UploadUIState.Idle)
                    }
                    .catch {
                        it.printStackTrace()
                        videoMap[uri]?.emit(UploadUIState.Error(it.message ?: "unknown error"))
                    }
                    .collect { uploadState ->
                        println(uploadState)
                        when (uploadState) {
                            is UploadState.Complete -> {
                                videoMap[uri]?.emit(UploadUIState.Success(uploadState.url))
                            }

                            UploadState.Idle -> {
                                videoMap[uri]?.emit(UploadUIState.Idle)
                            }

                            is UploadState.InProgress -> {
                                videoMap[uri]?.emit(UploadUIState.Progress(uploadState.progress.toFloat()))
                            }
                        }
                    }
            }
        }
    }

    fun removeUri(uri: Uri) {
        videoMap.remove(uri)
    }
}