package sp.remoteapplication

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import sp.remoteapplication.model.RemoteWindowsType
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
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        RemoteWindowsType(windowManager)
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
//                val packageName = "com.cisco.anyconnect.vpn.android.avf"
//                val isInstalled = isAppInstalled(context, packageName)
//                if (isInstalled) {
//                    // برنامه وجود دارد
//                    Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show()
//
//                    val intent = context.packageManager.getLaunchIntentForPackage(packageName)
//                    if (intent != null) {
//                        // We found the activity now start the activity
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//                        context.startActivity(intent)
//                    } else {
//                        Toast.makeText(context, "noop 2", Toast.LENGTH_SHORT).show()
//                    }
//                } else {
//                    // برنامه وجود ندارد
//                    Toast.makeText(context, "noop", Toast.LENGTH_SHORT).show()
//                }

//                Toast.makeText(context, "access!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
//                context.startActivity(intent)

//                Toast.makeText(context, "Service started!", Toast.LENGTH_SHORT).show()
//                val serviceIntent = Intent(context, RemoteAccessibilityService::class.java)
//                context.startService(serviceIntent)

//                val serviceIntent = Intent(context, MyService::class.java)
//                context.startService(serviceIntent)

                val intent =
                    context.packageManager.getLaunchIntentForPackage("com.cisco.anyconnect.vpn.android.avf")
                intent?.let {
                    it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(it)
                }

                val params = WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    PixelFormat.TRANSLUCENT
                )

                val view = View(context)
                view.setBackgroundColor(Color.TRANSPARENT)

                RemoteWindowsType.getPackageManager().addView(view, params)

            } catch (e: Exception) {
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
