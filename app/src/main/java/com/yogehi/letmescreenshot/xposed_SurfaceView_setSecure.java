package com.yogehi.letmescreenshot;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class xposed_SurfaceView_setSecure implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        Class<?> surfaceViewClass = XposedHelpers.findClass("android.view.SurfaceView", lpparam.classLoader);

        findAndHookMethod(surfaceViewClass, "setSecure", boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                param.args[0] = false;
            }
        });
    }
}