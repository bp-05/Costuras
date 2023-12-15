package cl.ingsoftware.costuras.ui.users.productos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.useradapter.AdaptadorUser

class ProductosUser : AppCompatActivity() {

    private val userViewModel : ProductosUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos_user)

        userViewModel.productos.observe(this, Observer { list ->
            Log.d("testeo", "desde users, : "+list.toString())
            val recyclerProductos = findViewById<RecyclerView>(R.id.recyclerUser)
            recyclerProductos.layoutManager = LinearLayoutManager(this)
            recyclerProductos.adapter = AdaptadorUser(list)
        })
    }
}