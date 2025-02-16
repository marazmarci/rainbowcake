package co.zsmb.rainbowcake.internal.livedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A [MutableLiveData] implementation that may only have a single observer
 * at a time, and delivers each value set in it only once at most.
 */
internal open class SingleShotLiveData<T : Any> : MutableLiveData<T>() {

    private val pending = AtomicBoolean(false)

    private var currentObserver: Observer<T>? = null
    private var observerWrapper: Observer<T>? = null

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
        if (currentObserver != null) {
            throw IllegalStateException("SingleShotLiveData may only have a single observer")
        }

        currentObserver = observer

        val wrapper = Observer<T> { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
        observerWrapper = wrapper

        super.observe(owner, wrapper)
    }

    override fun removeObserver(observer: Observer<T>) {
        val isCurrentObserver = observer === currentObserver
        val isObserverWrapper = observer === observerWrapper

        if (isCurrentObserver || isObserverWrapper) {
            observerWrapper?.let { super.removeObserver(it) }

            currentObserver = null
            observerWrapper = null
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

}
