import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicationReminderViewModel : ViewModel() {
    private val _medicationList = MutableLiveData<List<Pair<String, String>>>(emptyList())
    val medicationList: LiveData<List<Pair<String, String>>> get() = _medicationList

    private val _hasReminders = MutableLiveData(false)
    val hasReminders: LiveData<Boolean> get() = _hasReminders

    fun addReminder(medication: String, time: String) {
        val currentList = _medicationList.value?.toMutableList() ?: mutableListOf()
        currentList.add(Pair(medication, time))
        _medicationList.value = currentList

        _hasReminders.value = currentList.isNotEmpty()
    }

    fun removeReminder(medication: String, time: String) {
        val currentList = _medicationList.value?.toMutableList() ?: mutableListOf()
        currentList.remove(Pair(medication, time))
        _medicationList.value = currentList

        _hasReminders.value = currentList.isNotEmpty()
    }
}
