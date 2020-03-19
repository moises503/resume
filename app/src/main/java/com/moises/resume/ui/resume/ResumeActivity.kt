package com.moises.resume.ui.resume

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.moises.domain.resume.model.Profile
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumeView
import com.moises.resume.R
import com.moises.resume.ResumeApp
import com.moises.resume.core.deviceHasInternetConnection
import com.moises.resume.core.setToolbar
import com.moises.resume.ui.resume.adapters.ExperienceAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar_toolbar.*

class ResumeActivity : AppCompatActivity(), ResumeView {

    private lateinit var presenter : ResumePresenter
    private lateinit var adapter : ExperienceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupInjection()
    }

    override fun onResume() {
        super.onResume()
        presenter.attemptGetResume(applicationContext.deviceHasInternetConnection())
    }

    override fun showLoading() {
        pbLoader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoader.visibility = View.GONE
    }

    override fun displayProfile(profile: Profile) {
        Picasso.get().load(profile.photo).fit()
            .centerCrop().into(imgvPhoto)
        txtFullName.text = String.format(profile.fullName.orEmpty(), "Nombre")
        txtContactInfo.text = String.format(profile.contactInfo.orEmpty(), "E-mail", "Teléfono")
        txtLibs.text = profile.libs
        txtPatterns.text = profile.archs
        adapter.updateDataSet(profile.experience.orEmpty())
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun showViews() {
        this.showViews(View.VISIBLE)
    }

    override fun hideViews() {
        this.showViews(View.GONE)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    private fun setupInjection() {
        presenter = (application as ResumeApp).getResumeComponent(this, this).getResumePresenter()
    }

    private fun setupViews() {
        adapter = ExperienceAdapter(emptyList())
        this.setToolbar(toolbar, toolbar_title, getString(R.string.title_resume), false)
        rvExperience?.apply {
            layoutManager = LinearLayoutManager(this@ResumeActivity)
            adapter = this@ResumeActivity.adapter
            isNestedScrollingEnabled = false
        }
    }

    private fun showViews(visibility : Int){
        imgvPhoto.visibility = visibility
        txtFullName.visibility = visibility
        txtContactInfo.visibility = visibility
        txtLibs.visibility = visibility
        txtPatterns.visibility = visibility
        txtExperienceText.visibility = visibility
        rvExperience.visibility = visibility
        txtLibsText.visibility = visibility
        txtPatternsText.visibility = visibility
    }
}
