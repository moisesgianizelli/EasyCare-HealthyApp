import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppointmentViewModel : ViewModel() {
    private val _appointmentList = MutableLiveData<List<String>>(mutableListOf())
    val appointmentList: LiveData<List<String>> get() = _appointmentList

    private val _hasAppointments = MutableLiveData(false)
    val hasAppointments: LiveData<Boolean> get() = _hasAppointments

    fun addAppointment(appointment: String) {
        val currentList = _appointmentList.value?.toMutableList() ?: mutableListOf()
        currentList.add(appointment)
        _appointmentList.value = currentList

        _hasAppointments.value = currentList.isNotEmpty()
    }

    fun hasAppointments(): Boolean {
        return _appointmentList.value?.isNotEmpty() ?: false
    }
}

