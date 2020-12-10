package kr.intin.ble_kotlin.ui.mainFragments

import android.Manifest
import android.bluetooth.le.ScanResult
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.intin.ble_kotlin.R
import kr.intin.ble_kotlin.adapter.BLEAdapter
import kr.intin.ble_kotlin.databinding.FragmentMainBinding
import kr.intin.ble_kotlin.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    val model: MainViewModel by activityViewModels()
    val TAG = MainFragment::class.java.simpleName
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        it[Manifest.permission.ACCESS_FINE_LOCATION].let { fineBool ->
            if (fineBool!!) {
                val date = SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(Date())
                Toast.makeText(requireContext(), "위치권한 : $date", Toast.LENGTH_SHORT).show()
                model.scan()
            } else {
                Toast.makeText(requireContext(), "위치권한에 동의해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
        } else {
            model.scan()
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BLEAdapter(model)
        binding.recycler.adapter = adapter

        model.scanResultLiveData.observe(requireActivity(), Observer {
            if (it?.device?.name?.contains("OVIEW") == true) {
                adapter.addResult(it)
            }
        })

        adapter.setItemClickListener(object : BLEAdapter.ItemClickListener {
            override fun onClick(scanResult: ScanResult?) {

                if(findNavController().currentDestination?.id == R.id.mainFragment){
                    model.connect(scanResult, context)
                    findNavController().navigate(R.id.action_mainFragment_to_communicateFragment)
                    adapter.clearList()
                }
            }
        })
    }
}