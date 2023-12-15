package cl.ingsoftware.costuras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible
import cl.ingsoftware.costuras.ui.admin.Administrar
import cl.ingsoftware.costuras.ui.users.Perfil
import cl.ingsoftware.costuras.ui.users.productos.ProductosUser
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentUser = Firebase.auth.currentUser

        val boton_adm = findViewById<View>(R.id.boton_go_admin) as Button
        boton_adm.isVisible=false
        if (currentUser != null) {
            if (currentUser.uid == "JRcV6i2tVJTdpzHqSHU8rPIQJHY2"){
                boton_adm.isVisible=true
            }
        }
        boton_adm.setOnClickListener {
            val intentAdm = Intent(this, Administrar::class.java)
            startActivity(intentAdm)
        }

        val boton_perfil = findViewById<View>(R.id.boton_go_perfil) as Button
        boton_perfil.setOnClickListener {
            val intentPerfil = Intent(this, Perfil::class.java)
            startActivity(intentPerfil)
        }

        val boton_productos = findViewById<View>(R.id.boton_go_productos) as Button
        boton_productos.setOnClickListener {
            val intentProductos = Intent(this, ProductosUser::class.java)
            startActivity(intentProductos)
        }
    }
}