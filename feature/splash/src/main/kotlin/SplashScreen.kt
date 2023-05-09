import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.arkivanov.decompose.router.stack.replaceAll
import com.example.core.design_system.Images
import core.common.NavConfig
import core.common.navigation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    Image(
        painter = painterResource(id = Images.splash),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    LaunchedEffect(Unit) {
        val isLogin = token.isNotBlank()
        delay(2000L)
        navigation.replaceAll(if (isLogin) NavConfig.Home else NavConfig.Login)
    }
}