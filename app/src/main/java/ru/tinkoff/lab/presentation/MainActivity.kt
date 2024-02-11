package ru.tinkoff.lab.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tinkoff.lab.App
import ru.tinkoff.lab.R
import ru.tinkoff.lab.data.repository.FilmsListRepositoryImpl
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: FilmsListRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController = this.findNavController(R.id.fragment_container)

    }
}