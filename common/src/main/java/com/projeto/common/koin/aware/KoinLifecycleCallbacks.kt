package com.projeto.common.koin.aware

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.projeto.common.koin.aware.KoinAware
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class KoinLifecycleCallbacks : Application.ActivityLifecycleCallbacks,
    FragmentManager.FragmentLifecycleCallbacks() {

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        if (activity is KoinAware) {
            loadKoinModules(activity.subModules)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(this, true)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is KoinAware && !activity.subModules.keepAfterDestroy) {
            unloadKoinModules(activity.subModules)
        }
    }

    override fun onFragmentCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
        if (fragment is KoinAware) {
            loadKoinModules(fragment.subModules)
        }
    }

    override fun onFragmentDestroyed(fm: FragmentManager, fragment: Fragment) {
        if (fragment is KoinAware && !fragment.subModules.keepAfterDestroy) {
            unloadKoinModules(fragment.subModules)
        }
    }

    override fun onActivityStarted(p0: Activity) = Unit
    override fun onActivityResumed(p0: Activity) = Unit
    override fun onActivityPaused(p0: Activity) = Unit
    override fun onActivityStopped(p0: Activity) = Unit
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) = Unit

}