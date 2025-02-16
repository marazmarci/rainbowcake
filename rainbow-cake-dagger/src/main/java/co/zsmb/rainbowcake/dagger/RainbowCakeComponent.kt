package co.zsmb.rainbowcake.dagger

import android.arch.lifecycle.ViewModelProvider

/**
 * A base interface for the application's actual Dagger component that's
 * capable of providing a [ViewModelProvider.Factory] instance.
 */
interface RainbowCakeComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

}
