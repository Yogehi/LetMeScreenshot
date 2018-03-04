# LetMeScreenshot

Xposed module - attempts to disable `FLAG_SECURE` and `SurfaceView Secure` system wide. If an app tries to use either of those two methods to prevent screenshots, this Xposed module should allow screenshots.

### FLAG_SECURE

The class `android.view.Window` has a method called `setFlags` which looks like the following:

```
public void setFlags(int flags, int mask) {
        final WindowManager.LayoutParams attrs = getAttributes();
        attrs.flags = (attrs.flags&~mask) | (flags&mask);
        mForcedWindowFlags |= mask;
        dispatchWindowAttributesChanged(attrs);
    }
```

If the parameter `flags` is set to `8192` then the OS sets that specific window to `FLAG_SECURE`. This Xposed module sets the flag to `0` every time a window tries to set this flag to `8192`.

### SurfaceView

The class `android.view.SurfaceView` has a method called `setSecure` which looks like the following:

```
public void setSecure(boolean isSecure) {
        if (isSecure) {
            mSurfaceFlags |= SurfaceControl.SECURE;
        } else {
            mSurfaceFlags &= ~SurfaceControl.SECURE;
        }
    }
```

If the parameter `isSecure` is set to `True` then the OS sets that specific window to `Secure`. This Xposed module sets the boolean to `Flase` every time a window tries to set this boolean to `True`.
