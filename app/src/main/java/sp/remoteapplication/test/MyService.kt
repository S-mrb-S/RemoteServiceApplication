package sp.remoteapplication.test

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import sp.remoteapplication.MainActivity
import sp.remoteapplication.ScrollingActivity

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val transparentIntent = Intent(this, ScrollingActivity::class.java)
        transparentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(transparentIntent)

        val targetAppIntent = packageManager.getLaunchIntentForPackage("com.cisco.anyconnect.vpn.android.avf")
        if (targetAppIntent != null) {
            Log.d("N", "STARTED")
            startActivity(targetAppIntent)
        } else {
            // Handle the case where the target app is not installed
            Log.d("N", "NOT INSTALLED")
        }

        return START_NOT_STICKY
    }
}
