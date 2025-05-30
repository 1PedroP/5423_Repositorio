package pt.iefp.pokedex_nova_tentativa.api

import pt.iefp.pokedex_nova_tentativa.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>
}
