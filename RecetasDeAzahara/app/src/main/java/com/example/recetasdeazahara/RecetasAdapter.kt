package com.example.recetasdeazahara

import com.example.recetasdeazahara.model.Receta

class RecetasAdapter private var list: MutableList<Receta>,
private var listener:ActionOnClickListener)
: RecyclerView.Adapter<RecetasAdapter.ViewHolder>() {}