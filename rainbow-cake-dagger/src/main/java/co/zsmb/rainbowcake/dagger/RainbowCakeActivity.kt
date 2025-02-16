package co.zsmb.rainbowcake.dagger

import android.arch.lifecycle.ViewModelProviders
import co.zsmb.rainbowcake.base.RainbowCakeActivity
import co.zsmb.rainbowcake.base.RainbowCakeViewModel

/**
 * Uses the ViewModelFactory from the [RainbowCakeComponent] inside the [RainbowCakeApplication] to
 * fetch the appropriate ViewModel instance for the Activity.
 */
inline fun <A : RainbowCakeActivity<VS, VM>, VS, reified VM : RainbowCakeViewModel<VS>> A.getViewModelFromFactory(): VM {
    val viewModelFactory = (this.getApplicationContext() as? RainbowCakeApplication)
            ?.injector
            ?.viewModelFactory()
            ?: throw IllegalStateException("InjectedFragment should not be used without an Application that inherits from RainbowCakeApplication")

    return ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}
