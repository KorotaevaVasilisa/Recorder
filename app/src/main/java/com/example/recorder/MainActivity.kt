package com.example.recorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recorder.screens.MainScreen
import com.example.recorder.ui.theme.RecorderTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import com.vk.dto.common.id.UserId
import com.vk.sdk.api.base.dto.BaseUploadServerDto
import com.vk.sdk.api.docs.DocsService


class MainActivity : ComponentActivity() {

    private val tokenTracker = object: VKTokenExpiredHandler {
        override fun onTokenExpired() {
            auth()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecorderTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
        auth()
        VK.addTokenExpiredHandler(tokenTracker)

        //VK.execute(DocsService().docsSave(""))
    }

    private fun auth(){
        val authLauncher = VK.login(this) { result : VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    serverAddress(result.token.userId)
                }
                is VKAuthenticationResult.Failed -> {
                    // User didn't pass authorization
                    println()
                }
            }
        }
        authLauncher.launch(arrayListOf(VKScope.DOCS))
    }

    private fun serverAddress(userId:UserId){
        VK.execute(DocsService().docsGetUploadServer(userId), object : VKApiCallback<BaseUploadServerDto>{
            override fun fail(error: Exception) {
                println()
            }

            override fun success(result: BaseUploadServerDto) {
                println()
            }
        })
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecorderTheme {

    }
}
