package core.network.api

import com.example.android.core.model.SuccessCase
import com.example.android.core.model.UploadResponse
import core.network.Resp
import core.network.RespWithoutData
import core.network.UploadState
import httpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

suspend fun Apis.SuccessCases.getAll() = callbackFlow {
    httpClient.get("shares").apply {
        if (status.isSuccess()) {
            val resp = body<Resp<List<SuccessCase>>>()
            if (resp.code == 200) {
                send(resp.data)
            } else this@callbackFlow.cancel(resp.msg)
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}

suspend fun Apis.SuccessCases.getMyCases() = callbackFlow {
    httpClient.get("filter/me/shares").apply {
        if (status.isSuccess()) {
            val resp = body<Resp<List<SuccessCase>>>()
            if (resp.code == 200) {
                send(resp.data)
            } else this@callbackFlow.cancel(resp.msg)
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}

suspend fun Apis.SuccessCases.deleteMyCase(caseId: Int) = callbackFlow {
    httpClient.delete("filter/shares/{id}") {
        parameter("id", caseId)
    }.apply {
        if (status.isSuccess()) {
            val resp = body<RespWithoutData>()
            if (resp.code == 200) {
                send(null)
            } else this@callbackFlow.cancel(resp.msg)
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}

suspend fun Apis.SuccessCases.uploadFile(filename: String, bytes: ByteArray) = callbackFlow<UploadState> {
    send(UploadState.Idle)
    httpClient.post("shares_upload") {
        setBody(
            MultiPartFormDataContent(
                formData {
                    append("file", bytes, Headers.build {
                        append(HttpHeaders.ContentType, ContentType.Any)
                        append(HttpHeaders.ContentDisposition, filename)
                    })
                },
                boundary = "WebAppBoundary"
            )
        )
        onUpload { bytesSentTotal, contentLength ->
            send(UploadState.InProgress((bytesSentTotal / contentLength.toDouble())))
        }
    }.apply {
        if (status.isSuccess()) {
            val resp = body<UploadResponse>()
            println(resp)
            send(UploadState.Complete(resp.url!!))
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}

suspend fun Apis.SuccessCases.publishCase(case: SuccessCase) = callbackFlow {
    httpClient.post("filter/shares") {
        contentType(ContentType.Application.Json)
        setBody(case)
    }.apply {
        if (status.isSuccess()) {
            val resp = body<RespWithoutData>()
            if (resp.code == 200) {
                send(null)
            } else this@callbackFlow.cancel(resp.msg)
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}

suspend fun Apis.SuccessCases.getCaseDetail(id: Int) = callbackFlow {
    httpClient.get("shares/${id}").apply {
        if (status.isSuccess()) {
            val resp = body<Resp<SuccessCase>>()
            if (resp.code == 200) {
                send(resp.data)
            } else this@callbackFlow.cancel(resp.msg)
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}

suspend fun Apis.SuccessCases.updateCase(case: SuccessCase) = callbackFlow {
    httpClient.put("/filter/shares/${case.id}") {
        contentType(ContentType.Application.Json)
        setBody(case)
    }.apply {
        if (status.isSuccess()) {
            val resp = body<RespWithoutData>()
            if (resp.code == 200) {
                send(null)
            } else this@callbackFlow.cancel(resp.msg)
        } else this@callbackFlow.cancel(status.description)
        awaitClose { }
    }
}