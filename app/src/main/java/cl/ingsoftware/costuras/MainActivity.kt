package cl.ingsoftware.costuras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import cl.ingsoftware.costuras.ui.admin.Administrar
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton_adm = findViewById<View>(R.id.boton_go_admin) as Button

        boton_adm.setOnClickListener {
            val intentAdm = Intent(this, Administrar::class.java)
            startActivity(intentAdm)
        }

        //TESTEO
        val image = findViewById<ImageView>(R.id.imagentest)
        Glide.with(image.context).load("https://firebasestorage.googleapis.com/v0/b/costuras-5843d.appspot.com/o/test.png?alt=media&token=6940750f-e832-4551-becc-c7d8e6c30f92").into(image)
    }
}