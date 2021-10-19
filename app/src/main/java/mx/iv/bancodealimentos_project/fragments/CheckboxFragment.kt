package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import mx.iv.bancodealimentos_project.R

class CheckboxFragment : Fragment() {
    private var listener: CallbackHelp? = null

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
}