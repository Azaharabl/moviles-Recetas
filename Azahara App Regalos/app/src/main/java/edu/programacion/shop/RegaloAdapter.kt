package edu.programacion.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.programacion.shop.databinding.ItemShopBinding

class RegaloAdapter(private var lista: MutableList<Regalo>, private var listener: RegaloOnClickListener):
    RecyclerView.Adapter<RegaloAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context //iniciamos el contexto

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_shop, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val s = lista.get(position)
        holder.bind(s)
        //onClick
       // holder.itemView.setOnClickListener { listener.onClick(store) }
        holder.setListener(s)

    }

    override fun getItemCount(): Int {
       return lista.size
    }

    fun add(r: Regalo) {
        if (!lista.contains(r)){
            lista.add(r)
            notifyItemInserted(lista.size-1)
        }
    }

    //funcion para el listado de datos
    fun setStores(storesList: MutableList<Regalo>) {
        this.lista = storesList //sustituimos la lista
        notifyDataSetChanged() //refrescar la lista
    }

    fun update(r: Regalo) {

        val index = lista.indexOf(r)

        if(index != -1) {
            lista.set(index, r)

            notifyItemChanged(index)
        }
    }

    fun delete(r: Regalo) {

        val index = lista.indexOf(r)

        if(index != -1) {
            lista.removeAt(index)

            notifyItemRemoved(index)
        }
    }


    //clase interna ViewHolder
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val binding = ItemShopBinding.bind(view)

        //funcion para enlazar los datos del XML y la clase store
        fun bind(r: Regalo){
            binding.textNameRegalo.text = r.regalo
            binding.textNamePersona.text = r.name
            binding.textFecha.text = r.fecha
            binding.textPrecio.text = r.precio
            binding.cbFavorite.isChecked = r.completado
        }

        //funcion de escuchas
        fun setListener(r: Regalo){
            binding.root.setOnClickListener { listener.onClick(r.id) }
            binding.root.setOnLongClickListener {
                listener.onDeleteStore(r)
                true
            }

            binding.cbFavorite.setOnClickListener { listener.onFavoriteStore(r) }
        }
    }
}