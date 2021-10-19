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


class BeVolunteerFragment : Fragment() {
    private var listener: CallbackVolunteer? = null

    //Se infla la vista y agrega un click listener al boton de mandar información
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_be_volunteer, container, false)

        val db = FirebaseFirestore.getInstance()

        val inputName = view.findViewById<EditText>(R.id.fragBeVolunteerEtName)
        val inputEmail = view.findViewById<EditText>(R.id.fragBeVolunteerEtEmail)
        val inputTelephone = view.findViewById<EditText>(R.id.fragBeVolunteerEtPhone)
        val btnSendData = view.findViewById<Button>(R.id.fragBeVolunteerBtnSend)

        // Click listener para boton de mandar informacion
        btnSendData.setOnClickListener {
           if (validate(inputName) && validate(inputEmail) && validate(inputTelephone, "phone")) {
               db.collection("volunteers").document(inputEmail.text.toString()).set(
                   hashMapOf("name" to inputName.text.toString(), "telephone" to inputTelephone.text.toString())
               )
               listener?.replaceFormResponseFragment()
           }
        }

        return view
    }

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

        listener = if (context is CallbackVolunteer) {
            context
        }else {
            throw RuntimeException("Must implement Callback in Activity")
        }
    }

    interface CallbackVolunteer {
        fun replaceFormResponseFragment()
    }
}