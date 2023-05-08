package core.component_base

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.compose.runtime.Stable

@Stable
sealed interface LoadUIState<out T> {
    object Loading : LoadUIState<Nothing>
    data class Success<out T>(val data: T) : LoadUIState<T>
    data class Error(val error: Throwable) : LoadUIState<Nothing>
}

@Stable
sealed interface PostUIState {
    object None : PostUIState
    object Loading : PostUIState
    object Success : PostUIState
    data class Error(val error: Throwable) : PostUIState
}

@Stable
sealed interface UploadUIState {
    object Idle : UploadUIState
    data class Error(val message: String) : UploadUIState
    data class Success(val url: String) : UploadUIState
    data class Progress(val percent: Float) : UploadUIState
}

fun Uri.getFileName(context: Context): String? {
    var fileName: String? = null
    val cursor = context.contentResolver.query(this, null, null, null, null)
    cursor?.use {
        val a = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if(a < 0) throw Exception("uri error")
        if (it.moveToFirst()) {
            fileName = it.getString(a)
        }
    }
    fileName?.let {
        if (it.lastIndexOf(".") != -1 && it.lastIndexOf(".") < it.length - 1) {
            fileName = it.substring(it.lastIndexOf(".") + 1)
        }
    }
    return fileName
}
