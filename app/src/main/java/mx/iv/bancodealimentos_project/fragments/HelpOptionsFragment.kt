package mx.iv.bancodealimentos_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import mx.iv.bancodealimentos_project.*

/**
 * Fragmento con menu de opciones para ayudar
 */
class HelpOptionsFragment : Fragment() {

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
            val intent = Intent(it.context, BeBeneficiaryActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}