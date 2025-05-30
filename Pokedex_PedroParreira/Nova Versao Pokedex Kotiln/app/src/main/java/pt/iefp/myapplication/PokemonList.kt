package pt.iefp.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import pt.iefp.myapplication.Adapter.Common.Common
import pt.iefp.myapplication.Adapter.PokemonListAdapter
import pt.iefp.myapplication.Retrofit.IPokemonList
import pt.iefp.myapplication.Retrofit.RetrofitClient


class PokemonList : Fragment() {

    private lateinit var recyclerView: RecyclerView

    internal var compositeDisposable = CompositeDisposable()
    internal var iPokemonList: IPokemonList

    init {
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val itemDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.spacing)
        recyclerView.addItemDecoration(itemDecoration)

        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(
            iPokemonList.listPokemon
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { pokemonDex ->
                    Common.pokemonList = pokemonDex.pokemon!!

                    val adapter = PokemonListAdapter(requireActivity(), Common.pokemonList)
                    recyclerView.adapter = adapter
                }
        )
    }
}




