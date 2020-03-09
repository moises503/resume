package com.moises.resume.core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun ViewGroup.inflate(layoutId: Int): View =
    LayoutInflater.from(context).inflate(layoutId, this, false)

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.setToolbar(toolbar : Toolbar, toolbarTitle : TextView,
                                 title : String, homeAsUpEnabled : Boolean) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayShowTitleEnabled(false)
    toolbarTitle.text = title
    this.supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
}

fun AppCompatActivity.setToolbar(toolbar : Toolbar, title : String, textStyle : Int, homeAsUpEnabled : Boolean) {
    toolbar.setTitleTextAppearance(this, textStyle)
    toolbar.title = title
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(homeAsUpEnabled)
}