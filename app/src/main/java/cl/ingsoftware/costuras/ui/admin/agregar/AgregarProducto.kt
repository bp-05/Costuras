package cl.ingsoftware.costuras.ui.admin.agregar

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import cl.ingsoftware.costuras.R

class AgregarProducto : AppCompatActivity() {

    //private val REQUEST_CODE = 23
    //private val nombre: EditText = findViewById(R.id.agregar_nombre)
    //private val precio: EditText = findViewById(R.id.agregar_precio)
    //private val stock: EditText = findViewById(R.id.agregar_stock)
    //private val error: TextView = findViewById(R.id.error_agregar)
    //private val aceptar: Button = findViewById(R.id.boton_aceptar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)

        val tomarFoto: Button = findViewById(R.id.tomar_foto)
        tomarFoto.setOnClickListener{
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitMap = intent?.extras?.get("data") as Bitmap
            val imagenlayout: ImageView = findViewById(R.id.imagenlayout)
            imagenlayout.setImageBitmap(imageBitMap)
        }
    }
}