package pt.iefp.pokedex_nova_tentativa

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import pt.iefp.pokedex_nova_tentativa.api.ApiService
import pt.iefp.pokedex_nova_tentativa.models.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private lateinit var retrofit: Retrofit
    private var pokeNumber: Int = 1

    private lateinit var btnBack: ImageButton
    private lateinit var btnForward: ImageButton
    private lateinit var txtPokeName: TextView
    private lateinit var img: ImageView

    private val imageRequest: String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBack = findViewById(R.id.btn_back)
        btnForward = findViewById(R.id.btn_forward)
        img = findViewById(R.id.img_pokemon)
        txtPokeName = findViewById(R.id.txt_name)

        btnBack.setOnClickListener {
            if (pokeNumber == 1) return@setOnClickListener
            pokeNumber--
            getData()
        }

        btnForward.setOnClickListener {
            pokeNumber++
            getData()
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        getData()
    }

    private fun getData() {
        val service = retrofit.create(ApiService::class.java)
        val responseCall = service.getPokemon(pokeNumber)

        responseCall.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (!response.isSuccessful) {
                    Log.e(TAG, "On Response failed: ${response.code()}")
                    Log.e(TAG, "On Response failed: ${response.errorBody()}")
                    return
                }

                val pokemon = response.body()
                pokemon?.let {
                    Log.d(TAG, "poke: ${it.name}")
                    Log.d(TAG, "order: ${it.order}")
                    Log.d(TAG, "sprite: ${it.sprites.other.dreamWorld.frontDefault}")

                    txtPokeName.text = "${it.name} - ${it.order}"

                    setPokemonImage()
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setPokemonImage() {
        Glide.with(this)
            .load("$imageRequest$pokeNumber.gif")
            .into(img)
    }
}

