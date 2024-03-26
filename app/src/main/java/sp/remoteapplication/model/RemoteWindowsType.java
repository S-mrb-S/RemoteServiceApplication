package sp.remoteapplication.model;

import android.view.WindowManager;

public class RemoteWindowsType {

    private static WindowManager packageManager = null;

    public RemoteWindowsType(WindowManager package_manager) {
        packageManager = package_manager;
    }

    public static WindowManager getPackageManager() {
        return packageManager;
    }

}
