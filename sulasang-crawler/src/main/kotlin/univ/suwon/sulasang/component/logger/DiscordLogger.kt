package univ.suwon.sulasang.component.logger

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class DiscordLogger(
    @Value("\${discord.url.success}")
    private val successWebHookURL: String,

    @Value("\${discord.url.fail}")
    private val failWebHookURL: String,

    private val okHttpClient: OkHttpClient,
) : ThirdPartyLogger {

    override fun log(msg: String, success: Boolean) {
        val request = buildRequest(msg, if (success) successWebHookURL else failWebHookURL)

        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
        }
    }

    private fun buildRequest(msg: String, url: String) = Request.Builder()
        .url(url)
        .post("{\"content\": \"$msg\"}".toRequestBody(MediaTypeJSON))
        .build()

    companion object {
        private val MediaTypeJSON = "application/json; charset=utf-8".toMediaType()
    }
}
