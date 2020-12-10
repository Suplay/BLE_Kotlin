package kr.intin.ble_kotlin

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.intin.ble_kotlin.adapter.BLEAdapter
import kr.intin.ble_kotlin.databinding.ActivityMainBinding
import kr.intin.ble_kotlin.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private val model : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        //binding.lifecycleOwner = this

//        val navController = findNavController(R.id.nav_host_fragment)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

    }
    /**
     * Used as a wrapper for data that is exposed via a LiveData that represents an event.
     */
    open class Event<out T>(private val content: T) {

        var hasBeenHandled = false
            private set // Allow external read but not write

        /**
         * Returns the content and prevents its use again.
         */
        fun getContentIfNotHandled(): T? {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                content
            }
        }

        /**
         * Returns the content, even if it's already been handled.
         */
        fun peekContent(): T = content
    }


//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration) or super.onSupportNavigateUp()
//    }

}