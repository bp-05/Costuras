package cl.ingsoftware.costuras.model.useradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R
import cl.ingsoftware.costuras.model.AdaptadorProductoViewholder
import cl.ingsoftware.costuras.model.Producto

class AdaptadorUser(val productos:List<Producto>): RecyclerView.Adapter<UserViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewholder(layoutInflater.inflate(R.layout.user_card_producto, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewholder, position: Int) {
        val item = productos[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}