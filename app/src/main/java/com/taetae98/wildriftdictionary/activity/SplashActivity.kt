package com.taetae98.wildriftdictionary.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taetae98.wildriftdictionary.repository.ChampionRepository
import com.taetae98.wildriftdictionary.repository.NewRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var newRepository: NewRepository

    @Inject
    lateinit var championRepository: ChampionRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking(Dispatchers.IO) {
            newRepository.update()
            championRepository.update()
        }

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}