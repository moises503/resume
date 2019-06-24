package com.moises.resume.ui.resume

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.moises.data.model.Profile
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumeView
import com.moises.resume.R
import com.moises.resume.ResumeApp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class ResumeActivity : AppCompatActivity(), ResumeView {

    private lateinit var presenter : ResumePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInjectionAndGetResume()
    }

    override fun showLoading() {
        pbLoader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoader.visibility = View.GONE
    }

    override fun displayProfile(profile: Profile) {
        Picasso.get().load(profile.photo).into(imgvPhoto)
        txtFullName.text = String.format(profile.fullName!!, "Nombre")
        txtContactInfo.text = String.format(profile.contactInfo!!, "E-mail", "Teléfono")
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun setupInjectionAndGetResume() {
        presenter = (application as ResumeApp).getResumeComponent(this, this).getResumePresenter()
        presenter.attemptGetResume()
    }
}
