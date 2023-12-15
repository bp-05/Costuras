package cl.ingsoftware.costuras.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.AdaptadorProducto

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
    }
}