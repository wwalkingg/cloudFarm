package core.network

sealed interface UploadState {
    object Idle : UploadState
    data class InProgress(val progress: Double) : UploadState
    object Complete : UploadState
}