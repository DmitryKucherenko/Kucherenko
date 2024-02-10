package ru.tinkoff.lab.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ru.tinkoff.lab.R
import ru.tinkoff.lab.data.repository.FilmsRepositoryImpl
import ru.tinkoff.lab.databinding.ActivityMainBinding
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)
    }
}