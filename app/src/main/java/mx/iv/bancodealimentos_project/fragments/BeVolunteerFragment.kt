package mx.iv.bancodealimentos_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
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

        val btnSendData = view.findViewById<Button>(R.id.fragBeVolunteerBtnSend)
        val btnReturnHome = view.findViewById<TextView>(R.id.fragBeVolunteerTvReturnHome)

        // Click listener para boton de mandar informacion
        btnSendData.setOnClickListener {
            Toast.makeText(it.context, "¡Gracias por tu interés! Pronto nos pondremos en contacto contigo", Toast.LENGTH_SHORT).show()
            btnReturnHome.isVisible = true
        }

        // Click listener para boton de regresa a pagina de ayuda
        btnReturnHome.setOnClickListener {
            val intent = Intent(it.context, HelpActivity::class.java)
            startActivity(intent)
        }

        return view
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