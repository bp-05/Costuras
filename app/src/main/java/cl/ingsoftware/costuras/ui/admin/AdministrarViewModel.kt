package cl.ingsoftware.costuras.ui.admin


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.ingsoftware.costuras.model.Producto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdministrarViewModel : ViewModel(){
    private val db = Firebase.firestore

    private val _productos = MutableLiveData<List<Producto>>().apply {
        val docRef = db.collection("productos")
        docRef.get()
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("testeo", "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val updatedList = ArrayList<Producto>()
                for (document in snapshot){
                    Log.d("testeo", "Document: "+document.data.toString())
                    updatedList.add(Producto(document.get("id").toString(),document.get("imagen").toString(), document.get("nombre").toString(), document.get("precio").toString(), document.get("stock").toString()))
                }
                value=updatedList
                Log.d("testeo", "value: "+value.toString())
            } else {
                Log.d("testeo", "Current data: null")
            }
        }
        Log.d("testeo", docRef.toString())
    }
    val productos: LiveData<List<Producto>> = _productos
}