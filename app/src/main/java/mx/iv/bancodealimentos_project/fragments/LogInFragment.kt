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
import java.lang.RuntimeException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento que muestra el formulario para iniciar sesion
 */
class LogInFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: Callback? = null

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
        // Infla el layout y agrega listeners a botones
        val view = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false)

        val btnLogIn = view.findViewById<Button>(R.id.loginBtnLogIn)
        val btnSignIn = view.findViewById<Button>(R.id.loginBtnSignIn)
        val inputEmail = view.findViewById<EditText>(R.id.loginEtEmail)
        val inputPassword = view.findViewById<EditText>(R.id.loginEtPassword)

        btnLogIn.setOnClickListener {
            if (validate(inputEmail) && validate(inputPassword)) {
                listener?.runLogIn(inputEmail.text.toString(), inputPassword.text.toString())
            }
        }

        btnSignIn.setOnClickListener {
            listener?.replaceFrag()
        }

        return view
    }

    private fun validate(field: EditText, type: String = ""): Boolean {
        if (field.text.toString().isEmpty()){
            field.error = "Campo requerido"
            return false
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if (context is Callback) {
            context
        } else {
            throw RuntimeException("Must implement Callback in Activity")
        }
    }


    interface Callback {
        fun replaceFrag()
        fun runLogIn(email: String, password: String)
        fun runSignIn(email: String, password: String)
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            LogInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}