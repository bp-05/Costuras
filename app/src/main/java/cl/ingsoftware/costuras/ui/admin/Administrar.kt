package cl.ingsoftware.costuras.ui.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.AdaptadorProducto
import cl.ingsoftware.costuras.ui.admin.agregar.AgregarProducto

class Administrar : AppCompatActivity() {

    private val adminViewModel : AdministrarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrar)

        adminViewModel.productos.observe(this, Observer { list ->
            Log.d("testeo", "desde administrar, : "+list.toString())
            val recyclerProductos = findViewById<RecyclerView>(R.id.recyclerAdmin)
            recyclerProductos.layoutManager = LinearLayoutManager(this)
            recyclerProductos.adapter = AdaptadorProducto(list)
        })

        val agregar = findViewById<Button>(R.id.admin_agregar)
        agregar.setOnClickListener{
            val intentAgregar = Intent(this, AgregarProducto::class.java)
            startActivity(intentAgregar)
        }
    }
}