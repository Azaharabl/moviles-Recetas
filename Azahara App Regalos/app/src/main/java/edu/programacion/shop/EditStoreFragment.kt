package edu.programacion.shop

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import edu.programacion.shop.databinding.FragmentEditStoreBinding
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime


class EditStoreFragment : Fragment() {

    private  lateinit var binding: FragmentEditStoreBinding
    private var mActivity: MainActivity? = null

    private var isEditMode: Boolean = false //para ver si estamos edit o añadiendo
    private var mStore: Regalo? = null  //variable para recoger la consulta a la basedatos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditStoreBinding.inflate(inflater, container, false)
        //return inflater.inflate(R.layout.fragment_edit_store, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //si el fragment tiene argument
        val id = arguments?.getInt("key_id", 0)

        if (id != null && id != 0){ //si tenemos id
            isEditMode = true //ponemos en modo de edit
            getStore(id) //funcion para recoger los datos           
            
            Toast.makeText(activity, id.toString(), Toast.LENGTH_SHORT).show()
        }else { //si no tenemos id
            isEditMode = false
            mStore = Regalo(name = "",
                regalo = "",
                precio = "",
                notas = "",
                fecha = LocalDateTime.now().toString(),
                completado = false
            )
            Toast.makeText(activity, id.toString(), Toast.LENGTH_SHORT).show()
        }

        mActivity = activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title = "Editar Regalo"

        setHasOptionsMenu(true)

        //binding.editPhotoUrl.addTextChangedListener { Glide.with(this).load(binding.editPhotoUrl.text.toString()).diskCacheStrategy(DiskCacheStrategy.ALL) //almacena en cache los datos.centerCrop().into(binding.imgPhoto)

    }

    private fun getStore(id: Int) {
        //realizamos la consulta con la corrutina
        lifecycleScope.launch {
            mStore = RegaloApplication.database.regaloDao().getStoreById(id)

            //realizada la consulta preguntamos por el id
            if (mStore != null) setUIStore(mStore!!)
        }
    }

    //funcion para pasar los elementos a la IU
    private fun setUIStore(r: Regalo) {
        with(binding){
            editName.setText(r.name)
            editPhone.setText(r.regalo)
            editWebsite.setText(r.notas)
            editNotas.setText(r.precio)
            //mostrar imagen
           // Glide.with(mActivity!!).load(r.photoUrl).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(imgPhoto)
        }
    }




    //crear el menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_store, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    //realizar segun la opcion del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> {
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save -> {

                if (mStore != null){
                    with(mStore!!){
                        name = binding.editName.text.toString().trim()
                        regalo = binding.editPhone.text.toString().trim()
                        notas = binding.editWebsite.text.toString().trim()
                        precio = binding.editNotas.text.toString()
                    }

                    //lanzamos una corrutina
                    lifecycleScope.launch {
                        //StoreApplication.database.storeDao().addStore(store)
                        //si estamos editando podemos modificar los datos
                        if (isEditMode) RegaloApplication.database.regaloDao().deleteStore(mStore!!)

                        Snackbar.make(binding.root,
                            "Tienda borrada",
                            Snackbar.LENGTH_SHORT).show()

                        mActivity?.onDeleteStore(mStore!!)

                    }
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }

        //return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity?.supportActionBar?.title = getString(R.string.app_name)
        mActivity?.hideFab(true)

        setHasOptionsMenu(false)
        super.onDestroy()
    }

    fun llamaraFuncionAdd(view: View) {

        if (mStore != null) {
            with(mStore!!) {
                name = binding.editName.text.toString().trim()
                regalo = binding.editPhone.text.toString().trim()
                notas = binding.editWebsite.text.toString().trim()
                precio = binding.editNotas.text.toString()
            }

            //lanzamos una corrutina
            lifecycleScope.launch {
                //StoreApplication.database.storeDao().addStore(store)
                //si estamos editando podemos modificar los datos
                if (isEditMode) RegaloApplication.database.regaloDao().addStore(mStore!!)

                Snackbar.make(
                    binding.root,
                    "Tienda modificada",
                    Snackbar.LENGTH_SHORT
                ).show()

                mActivity?.addStore(mStore!!)

            }
        }
            else{
            with(mStore!!) {
                name = binding.editName.text.toString().trim()
                regalo = binding.editPhone.text.toString().trim()
                notas = binding.editWebsite.text.toString().trim()
                precio = binding.editNotas.text.toString()
            }

            //lanzamos una corrutina
            lifecycleScope.launch {
                //StoreApplication.database.storeDao().addStore(store)
                //si estamos editando podemos modificar los datos
                if (isEditMode) RegaloApplication.database.regaloDao().addStore(mStore!!)

                Snackbar.make(
                    binding.root,
                    "Tienda creada",
                    Snackbar.LENGTH_SHORT
                ).show()

                mActivity?.addStore(mStore!!)
            }
            }

    }



}