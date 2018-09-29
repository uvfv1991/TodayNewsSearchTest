package admin1.example.com.orderdistsapp.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @Created by admin
 * @Created on 2018/9/11.
 **/
public class SnackbarUtil {

    /*
     * 避免可能会出现的Toast崩溃
     * */

    public static final int I = 1;
    public static final int D = 2;
    public static final int W = 3;
    public static final int E = 4; //color
    private static int BLUE = 0xff2195f3;
    private static int GREEN = 0xff4caf50;
    private static int ORANGE = 0xffffc107;
    private static int RED = 0xfff44336;

    /**
     * short duration * * @param view * @param text * @return
     */
    public static Snackbar shortSnackbar(View view, CharSequence text) {
        return shortSnackbar(view, text, 0);
    }

    /**
     * short duration * * @param view * @param text * @param type * @return
     */
    public static Snackbar shortSnackbar(View view, CharSequence text, int type) {
        return getSnackbar(view, text, type, Snackbar.LENGTH_SHORT);
    }

    /**
     * long duration * * @param view * @param text * @return
     */
    public static Snackbar longSnackbar(View view, CharSequence text) {
        return shortSnackbar(view, text, 0);
    }

    /**
     * long duration * * @param view * @param text * @param type * @return
     */
    public static Snackbar longSnackbar(View view, CharSequence text, int type) {
        return getSnackbar(view, text, type, Snackbar.LENGTH_LONG);
    }

    /**
     * get snackbar * * @param view * @param text * @param type * @param duration * @return
     */
    private static Snackbar getSnackbar(View view, CharSequence text, int type, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        if (type > 0) switchType(snackbar, type);
        return snackbar;
    }

    /**
     * set color by snackbar type * * @param snackbar * @param type
     */
    private static void switchType(Snackbar snackbar, int type) {
        switch (type) {
            case I:
                setSnackbarBgColor(snackbar, BLUE);
                break;
            case D:
                setSnackbarBgColor(snackbar, GREEN);
                break;
            case W:
                setSnackbarBgColor(snackbar, ORANGE);
                break;
            case E:
                setSnackbarBgColor(snackbar, RED);
                break;
        }
    }

    /**
     * set snackbar background color * * @param snackbar * @param color
     */
    private static void setSnackbarBgColor(Snackbar snackbar, int color) {
        if (snackbar == null) return;
        View view = snackbar.getView();
        if (view == null) return;
        view.setBackgroundColor(color);
    }
}

