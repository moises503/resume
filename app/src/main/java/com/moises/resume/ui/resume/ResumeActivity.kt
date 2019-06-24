package com.moises.resume.ui.resume

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.moises.data.model.Experience
import com.moises.data.model.Profile
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumeView
import com.moises.resume.R
import com.moises.resume.ResumeApp
import com.moises.resume.ui.resume.adapters.ExperienceAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class ResumeActivity : AppCompatActivity(), ResumeView {

    private lateinit var presenter : ResumePresenter
    private lateinit var adapter : ExperienceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupInjectionAndGetResume()
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
        txtFullName.text = String.format(profile.fullName!!, "Nombre")
        txtContactInfo.text = String.format(profile.contactInfo!!, "E-mail", "Teléfono")
        txtLibs.text = profile.libs
        txtPatterns.text = profile.archs
        adapter.updateDataSet(profile.experience as List<Experience>)
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

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupInjectionAndGetResume() {
        presenter = (application as ResumeApp).getResumeComponent(this, this).getResumePresenter()
        presenter.attemptGetResume()
    }

    private fun setupViews() {
        adapter = ExperienceAdapter(emptyList())
        rvExperience.apply {
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
