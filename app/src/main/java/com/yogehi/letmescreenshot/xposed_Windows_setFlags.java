package com.yogehi.letmescreenshot;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class xposed_Windows_setFlags implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        Class<?> androidWindowClass = XposedHelpers.findClass("android.view.Window", lpparam.classLoader);

        findAndHookMethod(androidWindowClass, "setFlags", int.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                int flag = (int) param.args[0];
                int mask = (int) param.args[1];
                if (flag == 8192 || mask == 8192) { //8192 = flag_secure
                    param.args[0] = 0;
                    param.args[1] = 0;
                }
            }
        });
    }
}
