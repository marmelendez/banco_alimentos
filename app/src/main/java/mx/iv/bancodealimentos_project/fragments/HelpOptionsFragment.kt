package mx.iv.bancodealimentos_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import mx.iv.bancodealimentos_project.CollectionPointActivity
import mx.iv.bancodealimentos_project.DonationsActivity
import mx.iv.bancodealimentos_project.R

import mx.iv.bancodealimentos_project.SolicitarAyuda
import mx.iv.bancodealimentos_project.VolunteerActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HelpOptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
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
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_help_options, container, false)

        val btnDonations = view.findViewById<CardView>(R.id.helpCvDonations)
        val btnVolunteers = view.findViewById<CardView>(R.id.helpCvVolunteers)
        val btnPickup = view.findViewById<CardView>(R.id.helpCvPickup)
        val btnAsk = view.findViewById<CardView>(R.id.helpCvAskHelp)

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
            val intent = Intent(it.context, SolicitarAyuda::class.java)
            startActivity(intent)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HelpOptionsFragment.
         */
        // TODO: Rename and change types and number of parameters
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