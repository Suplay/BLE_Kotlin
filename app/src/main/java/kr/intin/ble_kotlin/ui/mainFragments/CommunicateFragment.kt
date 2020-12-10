package kr.intin.ble_kotlin.ui.mainFragments

import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.intin.ble_kotlin.R
import kr.intin.ble_kotlin.adapter.ChatAdapter
import kr.intin.ble_kotlin.databinding.FragmentCommunicateBinding
import kr.intin.ble_kotlin.ui.Sub.SubActivity
import kr.intin.ble_kotlin.viewmodel.MainViewModel

@AndroidEntryPoint
class CommunicateFragment : Fragment() {

    private lateinit var binding: FragmentCommunicateBinding
    private val adapter : ChatAdapter = ChatAdapter()
    private val model : MainViewModel by activityViewModels()
    private val TAG = CommunicateFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_communicate, container, false)
        binding.recyclerReceiveMsg.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.model = model
        model.responseData.observe(requireActivity(), Observer {
            //Adapter에 추가.
            adapter.addResponse(it)
        })

        model.connectState.observe(requireActivity(), Observer {
            if (it == BluetoothProfile.STATE_DISCONNECTED) {
                if(findNavController().currentDestination?.id == R.id.communicateFragment){
                    Toast.makeText(context, "연결이 취소되었습니다.", Toast.LENGTH_SHORT).show()
                    //findNavController().navigate(R.id.action_communicateFragment_to_resultFragment)
                    val intent = Intent(context, SubActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        model.usedTimer.observe(requireActivity(), Observer {
            binding.tvTimer.text = model.timeString(it)
        })

    }

}