package pt.iefp.myapplication.Retrofit

import io.reactivex.rxjava3.core.Observable
import pt.iefp.myapplication.Model.Pokedex
import pt.iefp.myapplication.Model.Pokemon
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>

}