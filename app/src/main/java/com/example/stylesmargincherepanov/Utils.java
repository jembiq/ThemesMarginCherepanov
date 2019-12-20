package com.example.stylesmargincherepanov;

import android.app.Activity;

public class Utils {
    private static int sTheme;

    public final static int THEME_SMALL_INDENT = 0;
    public final static int THEME_MIDDLE_INDENT= 1;
    public final static int THEME_BIG_INDENT = 2;

    public static void changeToTheme(int theme) {
        sTheme = theme;
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (sTheme) {
            case THEME_SMALL_INDENT :
                activity.setTheme(R.style.SmallIndentTheme);
                break;
            case THEME_MIDDLE_INDENT :
                activity.setTheme((R.style.MiddleIndentTheme));
                break;
            case THEME_BIG_INDENT :
                activity.setTheme(R.style.BigIndentTheme);
                break;
            default:
        }
    }
}
