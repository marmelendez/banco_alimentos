package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import mx.iv.bancodealimentos_project.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento con dos checkbox: solicitar ayuda para mi o para otra persona
 **/
class CheckboxFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CallbackHelp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // Se infla la vista y agrega listener a cada checkbox
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_checkbox, container, false)
        val checkboxExtHelp = view.findViewById<CheckBox>(R.id.checkboxCbExternalHelp)
        val checkboxPerHelp = view.findViewById<CheckBox>(R.id.checkboxCbPersonalHelp)

        // Si se selecciona el checkbox de ayuda para otra persona
        // se muestra el fragmento de external help
        checkboxExtHelp.setOnCheckedChangeListener { _, _ -> listener?.replaceExternalHelpFragment() }

        // Si se selecciona el checkbox de ayuda para mi
        // se muestra el fragmento de personal help
        checkboxPerHelp.setOnCheckedChangeListener { _, _ -> listener?.replacePersonalHelpFragment("", "", "") }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if (context is CallbackHelp) {
            context
        }else {
            throw RuntimeException("Must implemente Callback in Activity")
        }
    }

    interface CallbackHelp {
        fun replaceExternalHelpFragment()
        fun replacePersonalHelpFragment(name: String, email: String, phone: String)
        fun replaceResponseFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CheckboxFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}