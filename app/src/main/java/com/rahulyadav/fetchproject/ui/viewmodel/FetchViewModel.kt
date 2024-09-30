package com.rahulyadav.fetchproject.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulyadav.fetchproject.base.UiState
import com.rahulyadav.fetchproject.data.model.HiringData
import com.rahulyadav.fetchproject.data.repository.FetchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FetchViewModel(private val fetchRepository: FetchRepository): ViewModel() {

    private var selectedList = listOf<HiringData>()
    init {
        getHiringList()
    }

    private val _uiState = MutableStateFlow<UiState<List<HiringData>>>(UiState.Loading)
    val uiState : StateFlow<UiState<List<HiringData>>> = _uiState



    private fun getHiringList() {
        viewModelScope.launch {
            fetchRepository.getHiringData()
                .catch { error ->
                    _uiState.value = UiState.Error(error.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                    selectedList = it
                }
        }
    }

    /**
     * Filtering all ListIds from the hiring list for setting up in the dropdown.
     * also, adding "All" at the beginning to allow showing all items.
     */
    fun getFilteredItemByListId(hiringList: List<HiringData>): ArrayList<String> {
        val listIdOptions = ArrayList<String>()
        listIdOptions.add("All")

        listIdOptions.addAll(
            hiringList.map { it.hiringListId.toString() }.distinct()
        )
        listIdOptions.sort()

        return listIdOptions
    }

    /**
     * if user select any value from drop down then you need to filter the data gain from the list
     */
    fun getSelectedDropDownList(positionName:String):List<HiringData> {
        return if (positionName == "All") {
            selectedList
        } else{
            selectedList.filter { it.hiringListId.toString().equals(positionName, false) }
        }
    }


}