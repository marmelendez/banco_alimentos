package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import mx.iv.bancodealimentos_project.R
import java.lang.RuntimeException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento con el formulario para registrarse
 */
class SignInFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: LogInFragment.Callback? = null

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
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val btnSignIn = view.findViewById<Button>(R.id.registerBtnSignIn)
        val btnReturn = view.findViewById<TextView>(R.id.registerTvReturn)
        val inputEmail = view.findViewById<EditText>(R.id.registerEtEmail)
        val inputPassword = view.findViewById<EditText>(R.id.registerEtPassword)
        val inputConfirmPassword = view.findViewById<EditText>(R.id.registerEtConfirm)

        btnSignIn.setOnClickListener {
            if (inputPassword.text.toString() == inputConfirmPassword.text.toString()) {
                listener?.runSignIn(inputEmail.text.toString(), inputPassword.text.toString())
            } else {
                Toast.makeText(it.context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }

        }

        btnReturn.setOnClickListener {
            listener?.replaceFrag()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if (context is LogInFragment.Callback) {
            context
        } else {
            throw RuntimeException("Must implement Callback in Activity")
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}