package sp.remoteapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import sp.remoteapplication.ui.theme.RemoteApplicationTheme


class MainActivity : ComponentActivity() {
    private var packageManager: PackageManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemoteApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting("Android")
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton("Show Message")
                    }
                }
            }
        }

        packageManager = getPackageManager()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontSize = 24.sp
    )
}

fun isAppInstalled(context: Context, packageName: String): Boolean {
    val packageManager = context.packageManager
    return try {
        packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

@Composable
fun ActionButton(text: String) {
    val context = LocalContext.current

    Button(
        onClick = {
            try {
                val packageName = "com.cisco.anyconnect.vpn.android.avf"
                val isInstalled = isAppInstalled(context, packageName)
                if (isInstalled) {
                    // برنامه وجود دارد
                    Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show()
                } else {
                    // برنامه وجود ندارد
                    Toast.makeText(context, "noop", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception){
                Toast.makeText(context, "Ex", Toast.LENGTH_SHORT).show()
                Log.e("Exepction: ", e.toString())
            }
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RemoteApplicationTheme {
        Greeting("Android")
    }
}
