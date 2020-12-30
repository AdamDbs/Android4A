package com.example.androidkotlin.Retrofit

import android.database.Observable
import com.example.androidkotlin.model.Pokedex
import retrofit2.http.GET
import java.util.*

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>
}