package sp.remoteapplication;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class RemoteAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo source = event.getSource();
        if (source != null) { //  && event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED
            // اینجا می‌توانید منطق خود را بر اساس رویدادهای دریافتی پیاده‌سازی کنید
            // مثال: چک کردن آیا عنصری با شناسه خاص کلیک شده است و اگر اینطور بود، عملیات مورد نظر را انجام دهید
            if ("com.android.chrome:id/settings_button".equals(source.getViewIdResourceName())) {
                // انجام عملیات مورد نظر، مثلاً کلیک کردن روی دکمه تنظیمات
                performClickAction(source);
            }else{
                Log.d("On", "False!");
            }
        }else{
            Log.d("if", "False!");
        }
    }

    @Override
    public void onInterrupt() {
        // متدی که در صورت وقوع وقفه در سرویس فراخوانده می‌شود
        Log.d("On", "Interput");

    }

    private void performClickAction(AccessibilityNodeInfo node) {
        // انجام عملیات کلیک مورد نظر
        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        Log.d("On", "Clicked");
    }
}

