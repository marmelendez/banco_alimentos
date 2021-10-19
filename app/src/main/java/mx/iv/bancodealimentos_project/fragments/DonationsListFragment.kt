package mx.iv.bancodealimentos_project.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.R
import mx.iv.bancodealimentos_project.helper.DonationData
import mx.iv.bancodealimentos_project.helper.DonationsAdapter
import java.util.ArrayList

class DonationsListFragment : Fragment() {
    private lateinit var recyclerList: RecyclerView
    private lateinit var donationsList: MutableList<DonationData>
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
    }
}