package sp.remoteapplication.test

import android.app.Service
import android.content.Intent
import android.os.IBinder
import sp.remoteapplication.MainActivity

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val transparentIntent = Intent(this, MainActivity::class.java)
        transparentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(transparentIntent)

        val targetAppIntent = packageManager.getLaunchIntentForPackage("com.android.chrome")
        if (targetAppIntent != null) {
            startActivity(targetAppIntent)
        } else {
            // Handle the case where the target app is not installed
        }

        return START_NOT_STICKY
    }
}
