package cl.ingsoftware.costuras.ui.admin.agregar

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.Producto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.UUID

class AgregarProducto : AppCompatActivity() {

    private val db = Firebase.firestore
    private var fotosacada = false
    private lateinit var imageBitMap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)

        val tomarFoto: Button = findViewById(R.id.tomar_foto)
        val nombre: EditText = findViewById(R.id.agregar_nombre)
        val precio: EditText = findViewById(R.id.agregar_precio)
        val stock: EditText = findViewById(R.id.agregar_stock)
        val error: TextView = findViewById(R.id.error_agregar)
        val aceptar: Button = findViewById(R.id.boton_aceptar)

        tomarFoto.setOnClickListener{
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        aceptar.setOnClickListener{
            if (nombre.text.isNotEmpty() && precio.text.isNotEmpty() && stock.text.isNotEmpty() && fotosacada){

                val newDocumentRef = db.collection("productos").document()

                var url = ""
                val baos = ByteArrayOutputStream()
                var aux = imageBitMap
                aux.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                val randomname = "${UUID.randomUUID()}"
                val storageRef = FirebaseStorage.getInstance().getReference("${randomname}.jpg")
                var uploadTask = storageRef.putBytes(data)
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                            Log.d("testeo", "Error 1: "+it)
                        }
                    }
                    storageRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        url = task.result.toString()
                        Log.d("testeo", "URL: "+url)
                        val newProducto = Producto(newDocumentRef.id, url, nombre.text.toString(), precio.text.toString(), stock.text.toString())
                        val product = hashMapOf(
                            "id" to newProducto.id,
                            "imagen" to newProducto.imagen,
                            "nombre" to newProducto.nombre,
                            "precio" to newProducto.precio,
                            "stock" to newProducto.stock
                        )
                        newDocumentRef.set(product)
                        Toast.makeText(this, "producto agregado exitosamente", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Log.d("testeo", "no se subio la imagen, error: "+it)
                    }
                }
            }else{
                error.text = "Debes completar todos los campos"
            }
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            imageBitMap = intent?.extras?.get("data") as Bitmap
            val imagenlayout: ImageView = findViewById(R.id.imagenlayout)
            imagenlayout.setImageBitmap(imageBitMap)
            fotosacada=true
        }
    }
}