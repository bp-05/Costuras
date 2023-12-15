package cl.ingsoftware.costuras.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.AdaptadorProducto
import cl.ingsoftware.costuras.ui.auth.InicioSesion

class Perfil : AppCompatActivity() {

    private val perfilViewModel : PerfilViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val tv_email: TextView = findViewById(R.id.tvemail)
        perfilViewModel.tvemail.observe(this, Observer { string ->
            tv_email.text = string
        })

        val registeredtv: TextView = findViewById(R.id.tvregistered)
        perfilViewModel.registered.observe(this, Observer { string ->
            registeredtv.text = string
        })

        val lastontv: TextView = findViewById(R.id.tvlastacces)
        perfilViewModel.laston.observe(this, Observer { string ->
            lastontv.text = string
        })

        val cerrarSesion: Button = findViewById(R.id.boton_cerrar_sesion)
        cerrarSesion.setOnClickListener{
            perfilViewModel.cerrarSesion()
            val intentLogin = Intent(this, InicioSesion::class.java)
            intentLogin.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentLogin)
        }
    }
}