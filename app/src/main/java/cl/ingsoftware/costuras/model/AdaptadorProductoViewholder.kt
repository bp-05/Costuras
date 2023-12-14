package cl.ingsoftware.costuras.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import com.bumptech.glide.Glide

class AdaptadorProductoViewholder(view: View) : RecyclerView.ViewHolder(view){

    val imagen: ImageView = view.findViewById(R.id.image_admin_producto)
    val nombre: TextView = view.findViewById(R.id.admin_nombre)
    val precio: TextView = view.findViewById(R.id.admin_precio)
    val stock: TextView = view.findViewById(R.id.admin_stock)

    fun render(producto: Producto){
        Glide.with(imagen.context).load(producto.imagen).into(imagen)
        nombre.text = producto.nombre
        precio.text = producto.precio
        stock.text = producto.stock
    }
}