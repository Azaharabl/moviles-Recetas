package edu.programacion.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import edu.programacion.shop.databinding.ActivityMainBinding
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity(), RegaloOnClickListener , MainAux{


   lateinit var fragmentEdit : EditStoreFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var  mAdapter: RegaloAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTitle("Aplicacion Regalos Azahara")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //añadir datos a la base de datos utilizando corrutines
       /* binding.button.setOnClickListener {
            val store = Store(name = binding.editName.text.toString().trim())
            //lanzamos una corrutina
            lifecycleScope.launch {
                StoreApplication.database.storeDao().addStore(store)
            }

            mAdapter.add(store) //crear funcion en el adaptador para añadir elemento
        }*/
        //lanzamos el Fab
        binding.fab.setOnClickListener { launchEditfragment() }

        setupRecyclerView()
    }

    private fun launchEditfragment(args: Bundle? = null) {
        fragmentEdit = EditStoreFragment() //crear el fragment

        //preguntamos si hay argument
        if (args != null) fragmentEdit.arguments = args

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.containerMain, fragmentEdit) //ponemos el fragmento en
        fragmentTransaction.addToBackStack(null) //permite que se pueda volver atras
        fragmentTransaction.commit()

        //ocultamos el fab
       // binding.fab.hide()
        hideFab()
    }



    //funcion para crear el adaptador
    private fun setupRecyclerView() {
        mAdapter = RegaloAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this, 2)
        getStores() //llamamos al metodo para coger los datos en la database

        binding.recycler.apply {
            setHasFixedSize(true) //indicar que no cambia de tamaño
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    //consultar todos los datos de la dtabase lo vamos a realizar en un segundo hilo utilizando anko
    private fun getStores(){
        lifecycleScope.launch {
            //guardar en stores la lista de elementos de la base de datos
            val stores = RegaloApplication.database.regaloDao().getAllStore()

            //se lo notificamos al adaptador los datos
            mAdapter.setStores(stores)
        }
    }

    /* StoreOnClickListerne*/
    //funcion para mostrar una tienda
    override fun onClick(id: Int) {
        //vamos a pasar los datos desde Activity al fragment, utilizando Bundle
        val args = Bundle()
        args.putInt("key_id", id)

        //enviamos la tienda al fragmento para que se muestre
        launchEditfragment(args)
    }

    override fun onFavoriteStore(r: Regalo) {

        r.completado = !r.completado

        lifecycleScope.launch {
            RegaloApplication.database.regaloDao().updateStore(r)
            mAdapter.update(r)
        }
    }

     override fun onDeleteStore(r: Regalo) {
        lifecycleScope.launch {
            RegaloApplication.database.regaloDao().deleteStore(r)
            mAdapter.delete(r)
        }
    }

    /**
     * MainAux
     */
    override fun hideFab(isVisible: Boolean) {
        if(isVisible) binding.fab.show() else binding.fab.hide()
    }

    override fun addStore(r: Regalo) {
        mAdapter.add(r)
    }

    override fun updateStore(r: Regalo) {
        mAdapter.update(r)
    }
    fun onClickBotonAñadir(view: View) {
        fragmentEdit.llamaraFuncionAdd(view)

    }

}