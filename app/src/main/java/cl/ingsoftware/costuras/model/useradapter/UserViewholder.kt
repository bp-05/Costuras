package cl.ingsoftware.costuras.model.useradapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.Producto
import com.bumptech.glide.Glide

class UserViewholder(view: View) : RecyclerView.ViewHolder(view){

    val imagen: ImageView = view.findViewById(R.id.image_user_producto)
    val nombre: TextView = view.findViewById(R.id.user_nombre)
    val precio: TextView = view.findViewById(R.id.user_precio)
    val stock: TextView = view.findViewById(R.id.user_stock)

    fun render(producto: Producto){
        Glide.with(imagen.context).load(producto.imagen).into(imagen)
        nombre.text = producto.nombre
        precio.text = producto.precio
        stock.text = producto.stock
    }
}