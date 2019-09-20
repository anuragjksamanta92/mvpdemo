package com.basu.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.basu.R
import com.basu.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by root on 4/3/19.
 */

class LoginActivity : BaseActivity(), LoginMvpView {

    @Inject
    internal var mPresenter: LoginMvpPresenter<LoginMvpView, LoginMvpInteractor>? = null

    @BindView(R.id.ll_sing_up_to_activate)
    internal var ll_sing_up_to_activate: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)


        setContentView(R.layout.activity_login)

        activityComponent.inject(this)

        setUnBinder(ButterKnife.bind(this))

        mPresenter!!.onAttach(this@LoginActivity)



        ll_sing_up_to_activate!!.setOnClickListener { mPresenter!!.fetchMemberId(this@LoginActivity) }


    }


    /**
     * Making the screen wait so that the  branding can be shown
     */


    override fun onDestroy() {
        mPresenter!!.onDetach()
        super.onDestroy()
    }

    override fun setUp() {

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
