package com.example.androidkotlin

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlin.Adapter.PokemonListAdapter
import com.example.androidkotlin.Common.Common
import com.example.androidkotlin.Common.ItemOffsetDecoration
import com.example.androidkotlin.Retrofit.IPokemonList
import com.example.androidkotlin.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import java.util.concurrent.ScheduledExecutorService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler

class PokemonList : Fragment() {

    internal var compositeDisposable=CompositeDisposable()
    internal var iPokemonList:IPokemonList

    internal lateinit var recycler_view:RecyclerView

    init {
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val itemView =  inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recycler_view = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        pokemon_recyclerview.setHasFixedSize(true)
        pokemon_recyclerview.layoutManager = GridLayoutManager(activity, 2)

        val itemDecoration = ItemOffsetDecoration(Activity(), R.dimen.spacing)
        pokemon_recyclerview.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdapter(activity!!, Common.pokemonList)

                pokemon_recyclerview.adapter = adapter
            }
        )
    }


}
