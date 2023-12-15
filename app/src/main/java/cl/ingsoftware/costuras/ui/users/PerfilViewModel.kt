package cl.ingsoftware.costuras.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp

class PerfilViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val currentUser = Firebase.auth.currentUser

    private val _tvemail = MutableLiveData<String>().apply {
        val email = currentUser?.email.toString()
        value = "Email: $email"
    }

    val tvemail: LiveData<String> = _tvemail

    private val _registered = MutableLiveData<String>().apply {
        val fecha = Timestamp(currentUser?.metadata?.creationTimestamp!!).toString()
        val año = fecha.split(" ")[0].split("-")[0]
        val mes = fecha.split(" ")[0].split("-")[1]
        val dia = fecha.split(" ")[0].split("-")[2]
        value = "Registrado: "+año+"/"+mes+"/"+dia
    }
    val registered: LiveData<String> = _registered

    private val _laston = MutableLiveData<String>().apply {
        val fecha = Timestamp(currentUser?.metadata?.lastSignInTimestamp!!).toString()
        val año = fecha.split(" ")[0].split("-")[0]
        val mes = fecha.split(" ")[0].split("-")[1]
        val dia = fecha.split(" ")[0].split("-")[2]
        value="Ultimo acceso: "+año+"/"+mes+"/"+dia
    }
    val laston: LiveData<String> = _laston

    fun cerrarSesion(){
        Firebase.auth.signOut()
    }
}