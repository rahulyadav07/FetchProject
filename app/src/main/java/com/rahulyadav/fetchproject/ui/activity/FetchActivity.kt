package com.rahulyadav.fetchproject.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulyadav.fetchproject.FetchApplication
import com.rahulyadav.fetchproject.base.UiState
import com.rahulyadav.fetchproject.data.model.HiringData
import com.rahulyadav.fetchproject.databinding.ActivityFetchBinding
import com.rahulyadav.fetchproject.di.component.DaggerActivityComponent
import com.rahulyadav.fetchproject.di.module.ActivityModule
import com.rahulyadav.fetchproject.ui.adapter.FetchAdapter
import com.rahulyadav.fetchproject.ui.viewmodel.FetchViewModel
import com.rahulyadav.fetchproject.utils.gone
import com.rahulyadav.fetchproject.utils.show
import kotlinx.coroutines.launch
import javax.inject.Inject

class FetchActivity : AppCompatActivity() {

    @Inject
    lateinit var fetchViewModel: FetchViewModel

    private lateinit var binding: ActivityFetchBinding

    @Inject
    lateinit var fetchAdapter: FetchAdapter


    @Inject
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityFetchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setUpObserver()

    }

    private fun initViews() {
        /**
         * here setup the recycler view
         */
        val rv = binding.sourceRecylerView
        rv.layoutManager = LinearLayoutManager(this)
        rv.addItemDecoration(DividerItemDecoration(rv.context, (rv.layoutManager as LinearLayoutManager).orientation))
        rv.adapter = fetchAdapter

        /**
         * dropDown adapter
         */
        binding.filterSpinner.adapter = arrayAdapter



        /**
         * filter spinner listener
         */

        binding.filterSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedPosition = parent?.getItemAtPosition(position).toString()
                val filteredList = fetchViewModel.getSelectedDropDownList(selectedPosition)
                renderSourceList(filteredList)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fetchViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.apply {
                                progressBar.gone()
                                sourceRecylerView.show()
                                filterSpinner.show()
                                renderSourceList(it.data)
                                /**
                                 * set the listId in dropdown
                                 */
                               renderDropDownList(fetchViewModel.getFilteredItemByListId(it.data))
                            }

                        }
                        is UiState.Loading -> {
                            binding.apply {
                                progressBar.show()
                                sourceRecylerView.gone()
                                filterSpinner.gone()
                            }
                        }
                        is UiState.Error -> {
                            binding.apply {
                                progressBar.gone()
                                sourceRecylerView.gone()
                                filterSpinner.gone()
                                Toast.makeText(this@FetchActivity, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun renderSourceList(sourceList: List<HiringData>) {
        fetchAdapter.apply {
            addData(sourceList)
            notifyDataSetChanged()
        }
    }
    private fun renderDropDownList(dropDownList:List<String>) {
        arrayAdapter.addAll(dropDownList)
    }

    private fun injectDependencies()  {
        DaggerActivityComponent.builder()
            .applicationComponent((application as FetchApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build().injectFetchActivity(this)
    }
}