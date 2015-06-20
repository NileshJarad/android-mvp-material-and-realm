package com.nileshjarad.realmdemo.utility;

import android.os.Build;

/**
 * Created by Nilesh Jarad on 18-06-2015.
 */
public class Util {

    /**
     *This function check device OS Whether it is running Lollipop or higher
     * @return boolean - running android 5.0 or higher true or false
     */
    public static boolean isAndroid5() {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return true;

        return false;

    }
}
