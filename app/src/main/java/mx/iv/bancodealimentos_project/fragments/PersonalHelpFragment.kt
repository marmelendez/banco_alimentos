package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import mx.iv.bancodealimentos_project.R

/**
 * Fragmento con formulario de informacion para el apartado de
 * solicitar ayuda cuando se solicita para sí misma
 */
class PersonalHelpFragment(val data: List<String>) : Fragment() {

    private var listener: CheckboxFragment.CallbackHelp? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_personal_help, container, false)

        val db = FirebaseFirestore.getInstance()

        val inputName = view.findViewById<EditText>(R.id.perHelpEtName)
        val inputEmail = view.findViewById<EditText>(R.id.perHelpEtEmail)
        val inputTelephone = view.findViewById<EditText>(R.id.perHelpEtPhone)
        val next = view.findViewById<Button>(R.id.perHelpBtnAskHelp)

        // Click listener del boton siguiente
        // para mostrar el fragmento personal help
        next.setOnClickListener {
            if (validate(inputName) && validate(inputTelephone, "phone")) {
                if (data[0] != "") {
                    // Se guardan los datos ingresados al validarlos
                    // en caso de solicitar para otra persona
                    db.collection("beneficiary").document(inputTelephone.text.toString()).set(
                        hashMapOf(
                            "helper_name" to inputName.text.toString(),
                            "helper_email" to inputEmail.text.toString(),
                            "beneficiary_name" to data[0],
                            "beneficiary_email" to data[1],
                            "beneficiary_phone" to data[2])
                    )
                } else { // en caso de solicitar para si mismo
                    db.collection("beneficiary").document(inputTelephone.text.toString()).set(
                        hashMapOf(
                            "beneficiary_name" to inputName.text.toString(),
                            "beneficiary_email" to inputEmail.text.toString())
                    )
                }

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

    // Validar datos del usuario
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

}