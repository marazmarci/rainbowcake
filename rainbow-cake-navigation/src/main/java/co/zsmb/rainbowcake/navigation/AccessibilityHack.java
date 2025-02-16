package co.zsmb.rainbowcake.navigation;

import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import co.zsmb.rainbowcake.base.RainbowCakeFragment;

class AccessibilityHack {

    private AccessibilityHack() {
        // Non-instantiable class
    }

    @SuppressWarnings({"KotlinInternalInJava"})
    static void setOverrideAnimation(
            @NonNull final RainbowCakeFragment fragment,
            @AnimRes @Nullable final Integer overrideAnimation) {
        fragment.overrideAnimation(overrideAnimation);
    }

}
