package com.example.mobile_android.presentation.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_android.R
import com.example.mobile_android.presentation.user_list.UserAdapter
import com.example.mobile_android.presentation.user_list.UserViewModel
import com.example.mobile_android.presentation.user_list.UserViewModelFactory
import timber.log.Timber

class StartScreenFragment : Fragment() {
    private val viewModel by viewModels<UserViewModel> { UserViewModelFactory() }
    private val adapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoRecyclerView: RecyclerView = view.findViewById(R.id.photo_list)
        photoRecyclerView.adapter = adapter
        photoRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        initViewModel()
        viewModel.getUsers()
    }

    private fun initViewModel() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Timber.d(error)
        }
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartScreenFragment()
    }
}
