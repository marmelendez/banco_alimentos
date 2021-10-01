package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import mx.iv.bancodealimentos_project.AskHelpActivity
import mx.iv.bancodealimentos_project.HelpActivity
import mx.iv.bancodealimentos_project.R


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento con formulario para ofrecerse como voluntario
 **/
class BeVolunteerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: CallbackVolunteer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //Se infla la vista y agrega un click listener al boton de mandar información
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_be_volunteer, container, false)

        val inputName = view.findViewById<EditText>(R.id.fragBeVolunteerEtName)
        val inputEmail = view.findViewById<EditText>(R.id.fragBeVolunteerEtEmail)
        val inputTelephone = view.findViewById<EditText>(R.id.fragBeVolunteerEtPhone)
        val btnSendData = view.findViewById<Button>(R.id.fragBeVolunteerBtnSend)

        // Click listener para boton de mandar informacion
        btnSendData.setOnClickListener {
           if (validate(inputName) && validate(inputEmail) && validate(inputTelephone)) {
               listener?.replaceFormResponseFragment()
           }
        }

        return view
    }

    private fun validate(field: EditText): Boolean {
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BeVolunteerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}