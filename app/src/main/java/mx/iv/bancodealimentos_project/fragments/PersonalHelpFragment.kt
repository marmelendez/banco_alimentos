package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import mx.iv.bancodealimentos_project.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento con formulario de informacion para el apartado de
 * solicitar ayuda cuando se solicita para sí misma
 */
class PersonalHelpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CheckboxFragment.CallbackHelp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_personal_help, container, false)
        val inputName = view.findViewById<EditText>(R.id.perHelpEtName)
        val inputTelephone = view.findViewById<EditText>(R.id.perHelpEtPhone)
        val next = view.findViewById<Button>(R.id.perHelpBtnAskHelp)

        // Click listener del boton siguiente
        // para mostrar el fragmento personal help
        next.setOnClickListener {
            if (validate(inputName) && validate(inputTelephone)) {
                listener?.replaceResponseFragment()
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if (context is CheckboxFragment.CallbackHelp) {
            context
        }else {
            throw RuntimeException("Must implement Callback in Activity")
        }
    }

    private fun validate(field: EditText): Boolean {
        if (field.text.toString().isEmpty()){
            field.error = "Campo requerido"
            return false
        }
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalHelpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}