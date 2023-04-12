package com.konstan.ratesp2p.mvvm.presentation.activity

import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.konstan.ratesp2p.core.navigation.Navigator
import com.konstan.ratesp2p.core.presentation.BaseViewModel
import com.konstan.ratesp2p.mvvm.Screens
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: Router,
    private val holder: NavigatorHolder,
    private val inputMethodManager: InputMethodManager
) : BaseViewModel() {

    private var keyboardHandler: Unregistrar? = null
    val keyboardVisibility = MutableLiveData<Boolean>()
    private var isFirstAttach = true

    private val isKeyboardVisible: Boolean
        get() = keyboardVisibility.value ?: false

    fun setNavigator(navigator: Navigator) {
        holder.removeNavigator()
        holder.setNavigator(navigator)
        if (isFirstAttach) {
            router.replaceScreen(Screens.p2p())
            isFirstAttach = false
        }
    }

    fun closeKeyboard(view: View?) {
        if (isKeyboardVisible) {
            view?.apply {
                clearFocus()
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
            }
        }
    }

    fun initKeyboardHandler(activity: AppCompatActivity) {
        keyboardHandler = KeyboardVisibilityEvent.registerEventListener(activity) {
            keyboardVisibility.value = it
        }
    }

    fun detach() {
        keyboardHandler?.unregister()
        holder.removeNavigator()
    }

    fun showKeyboard(view: EditText) {
        view.requestFocus()
        if (!isKeyboardVisible) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }
}