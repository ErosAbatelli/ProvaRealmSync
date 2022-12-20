package com.example.bho.views
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.bho.R
import com.example.bho.di.Injection
import com.example.bho.viewModels.PersonViewModel
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeFragment : Fragment() {

    private lateinit var viewModel: PersonViewModel
    private val disposable = CompositeDisposable()


    private lateinit var namePersonInput: TextInputEditText
    private lateinit var agePersonInput: TextInputEditText
    private lateinit var applyPerson: Button
    private lateinit var getAll: Button
    private lateinit var button: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = PersonViewModel(Injection.providePersonLocalSource(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        button = view.findViewById<Button>(R.id.btnHome)
        namePersonInput = view.findViewById<TextInputEditText>(R.id.namePersonInput)
        agePersonInput = view.findViewById<TextInputEditText>(R.id.agePersonInput)
        getAll = view.findViewById<Button>(R.id.getAll)
        applyPerson = view.findViewById<Button>(R.id.applyPerson)


        var namePerson = ""
        var agePerson: Int = 0

        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_dataFragment)
        }

        applyPerson.setOnClickListener {
            if(namePersonInput.text!!.isNotEmpty()) namePerson = namePersonInput.text.toString()
            if(agePersonInput.text!!.isNotEmpty()) agePerson = agePersonInput.text.toString().toIntOrNull()!!

            disposable.clear()
            disposable.add(
                viewModel.insertPerson(namePerson, agePerson)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("bho", "ok")
                        Log.d("PROVA", "${it.name}, ${it.age}, ${it._id}")
                    }
            )
        }

        getAll.setOnClickListener {
            disposable.clear()
            disposable.add(
                viewModel.getAllPerson()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        it.forEach{
                            Log.d("Nome:", it.name)
                            Log.d("Age:", it.age.toString())
                            Log.d("Id:", it._id.toString())
                        }
                    }
            )
        }

        return view
    }
}