package com.polgomez.movies

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import com.polgomez.core.StoryActivity
import com.polgomez.movies.app.MoviesApp
import com.polgomez.movies.di.MoviesActivityComponent
import com.polgomez.movies.di.MoviesActivityModule
import com.polgomez.movies.story.MoviesStory

class MoviesActivity : StoryActivity<MoviesStory>() {

    lateinit var moviesActivityComponent: MoviesActivityComponent

    override fun getLayoutResource(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun initializeInjections() {
        val moviesApp = application as MoviesApp
        moviesActivityComponent =
                moviesApp.component.moviesActivityComponentBuilder().moviesActivityModule(MoviesActivityModule(this))
                    .build()
        moviesActivityComponent.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}
