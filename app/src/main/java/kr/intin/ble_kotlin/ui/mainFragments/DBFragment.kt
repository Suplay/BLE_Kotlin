package kr.intin.ble_kotlin.ui.mainFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.intin.ble_kotlin.R
import kr.intin.ble_kotlin.adapter.DBAdapter
import kr.intin.ble_kotlin.data.dao.UseTimeDAO
import kr.intin.ble_kotlin.data.entity.UseTime
import kr.intin.ble_kotlin.databinding.FragmentDbBinding
import javax.inject.Inject

@AndroidEntryPoint
class DBFragment : Fragment() {

    
    @Inject
    lateinit var db : UseTimeDAO
    private lateinit var binding : FragmentDbBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_db, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DBAdapter()
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO){
            adapter.list = db.getAll() as ArrayList<UseTime>
        } }

    }

}