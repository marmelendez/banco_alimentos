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

/**
 * Fragmento con formulario de datos de la persona que desea ser beneficiario
 */
class ExternalHelpFragment() : Fragment() {
    private var listener: CheckboxFragment.CallbackHelp? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_external_help, container, false)
        val inputName = view.findViewById<EditText>(R.id.extHelpEtName)
        val inputEmal = view.findViewById<EditText>(R.id.extHelpEtEmail)
        val inputTelephone = view.findViewById<EditText>(R.id.extHelpEtPhone)
        val next = view.findViewById<Button>(R.id.extHelpBtnNext)

        // Click listener del boton siguiente
        // para mostrar el fragmento personal help
        next.setOnClickListener {
            if (validate(inputName) && validate(inputTelephone, "phone")) {
                listener?.replacePersonalHelpFragment(inputName.text.toString(), inputEmal.text.toString(), inputTelephone.text.toString())
            }
        }
        return view
    }

    // Valida que los datos ingresados por el usuario sean correctos
    private fun validate(field: EditText, type: String = ""): Boolean {
        if (type == "phone" && field.text.toString().length != 10 ) {
            field.error = "10 numeros"
            return false
        }
        if (field.text.toString().isEmpty()){
            field.error = "Campo requerido"
            return false
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if (context is CheckboxFragment.CallbackHelp) {
            context
        }else {
            throw RuntimeException("Must implement Callback in Activity")
        }
    }
}