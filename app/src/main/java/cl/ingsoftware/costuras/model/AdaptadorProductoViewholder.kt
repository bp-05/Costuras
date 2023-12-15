package cl.ingsoftware.costuras.model

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class AdaptadorProductoViewholder(view: View) : RecyclerView.ViewHolder(view){

    val imagen: ImageView = view.findViewById(R.id.image_admin_producto)
    val nombre: TextView = view.findViewById(R.id.admin_nombre)
    val precio: TextView = view.findViewById(R.id.admin_precio)
    val stock: TextView = view.findViewById(R.id.admin_stock)
    val borrar: Button = view.findViewById(R.id.admin_eliminar)

    fun render(producto: Producto){
        Glide.with(imagen.context).load(producto.imagen).into(imagen)
        nombre.text = producto.nombre
        precio.text = producto.precio
        stock.text = producto.stock
        borrar.setOnClickListener{
            val db = Firebase.firestore
            val docRef = db.collection("productos").document(producto.id)
            docRef.delete().addOnSuccessListener {
                Log.d("testeo", "Documento eliminado con éxito!")
            }.addOnFailureListener { e ->
                Log.w("testeo", "Error al eliminar el documento", e)
            }
            //imagen
            val storage = FirebaseStorage.getInstance()
            val photoRef = storage.getReferenceFromUrl(producto.imagen)
            photoRef.delete().addOnSuccessListener {
                Log.d("testeo", "Imagen eliminada con éxito!")
            }.addOnFailureListener { e ->
                Log.w("testeo", "Error al eliminar la imagen", e)
            }
        }
    }
}