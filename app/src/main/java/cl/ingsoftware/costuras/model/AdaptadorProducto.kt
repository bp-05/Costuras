package cl.ingsoftware.costuras.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.ingsoftware.costuras.R

class AdaptadorProducto(val productos:List<Producto>): RecyclerView.Adapter<AdaptadorProductoViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorProductoViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AdaptadorProductoViewholder(layoutInflater.inflate(R.layout.admin_card_producto, parent, false))
    }

    override fun onBindViewHolder(holder: AdaptadorProductoViewholder, position: Int) {
        val item = productos[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}