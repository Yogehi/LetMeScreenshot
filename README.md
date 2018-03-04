# LetMeScreenshot

Xposed module - attempts to disable "FLAG_SECURE" and "SurfaceView Secure" system wide.

Flag_Secure

```
public void setFlags(int flags, int mask) {
        final WindowManager.LayoutParams attrs = getAttributes();
        attrs.flags = (attrs.flags&~mask) | (flags&mask);
        mForcedWindowFlags |= mask;
        dispatchWindowAttributesChanged(attrs);
    }
```
