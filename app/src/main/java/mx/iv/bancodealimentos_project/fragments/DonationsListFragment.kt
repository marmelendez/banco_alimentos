package mx.iv.bancodealimentos_project.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.R
import mx.iv.bancodealimentos_project.helper.DonationData
import mx.iv.bancodealimentos_project.helper.DonationsAdapter
import java.util.ArrayList
import com.google.firebase.firestore.DocumentSnapshot

import com.google.firebase.firestore.QueryDocumentSnapshot

import com.google.firebase.firestore.QuerySnapshot




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DonationsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DonationsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerList: RecyclerView
    private lateinit var donationsList: MutableList<DonationData>
    private val db = FirebaseFirestore.getInstance()

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
        val view =  inflater.inflate(R.layout.fragment_donations_list, container, false)

        donationsList = mutableListOf()
        getDonationsList(view)

        return view

    }

    private fun getDonationsList(view: View) {
        db.collection("donations")
            .whereEqualTo("email", Firebase.auth.currentUser?.email.toString())
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    donationsList.add(DonationData(document.id, document.data["amount"].toString(), document.data["date"].toString()))
                }
                recyclerList = view.findViewById(R.id.fragDonationsRvRecycler)
                recyclerList.layoutManager = LinearLayoutManager(context)
                val donationsAdapter = DonationsAdapter(donationsList as ArrayList<DonationData>)
                recyclerList.adapter = donationsAdapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
            }
        //donationsList.add(DonationData("0001", "123", "07/02/12"))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DonationsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DonationsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}