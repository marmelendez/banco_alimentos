package mx.iv.bancodealimentos_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import mx.iv.bancodealimentos_project.CollectionPointActivity
import mx.iv.bancodealimentos_project.DonationsActivity
import mx.iv.bancodealimentos_project.R

import mx.iv.bancodealimentos_project.AskHelpActivity
import mx.iv.bancodealimentos_project.VolunteerActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento que muestra el menu de opciones de como ayudar
 */
class HelpOptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        val view = inflater.inflate(R.layout.fragment_help_options, container, false)

        val btnDonations = view.findViewById<CardView>(R.id.helpCvDonations)
        val btnVolunteers = view.findViewById<CardView>(R.id.helpCvVolunteers)
        val btnPickup = view.findViewById<CardView>(R.id.helpCvPickup)
        val btnAsk = view.findViewById<CardView>(R.id.helpCvAskHelp)

        // Agrega listener a cada boton para redirigir a la pagina solicitada
        btnDonations.setOnClickListener {
            val intent = Intent(it.context, DonationsActivity::class.java)
            startActivity(intent)
        }

        btnVolunteers.setOnClickListener {
            val intent = Intent(it.context, VolunteerActivity::class.java)
            startActivity(intent)
        }

        btnPickup.setOnClickListener {
            val intent = Intent(it.context, CollectionPointActivity::class.java)
            startActivity(intent)
        }

        btnAsk.setOnClickListener {
            val intent = Intent(it.context, AskHelpActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HelpOptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}