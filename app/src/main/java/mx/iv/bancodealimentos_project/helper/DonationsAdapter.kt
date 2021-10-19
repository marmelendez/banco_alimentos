package mx.iv.bancodealimentos_project.helper

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.iv.bancodealimentos_project.R
import java.util.ArrayList

class DonationsAdapter(donationsList: ArrayList<DonationData>) : RecyclerView.Adapter<DonationsAdapter.DonationsAdapterViewHolder>()  {
    private val listItems: ArrayList<DonationData> = donationsList

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(viewHolder: DonationsAdapterViewHolder, position: Int) {
        val donationItem: DonationData = listItems[position]

        viewHolder.id.text = donationItem.id
        viewHolder.amount.text = "$ ${donationItem.amount}.00"
        viewHolder.date.text = donationItem.date
    }

    class DonationsAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var view: View = view
        var id: TextView = view.findViewById(R.id.donationItemTvIdValue)
        var amount: TextView = view.findViewById(R.id.donationItemTvAmountValue)
        var date: TextView = view.findViewById(R.id.donationItemTvDateValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationsAdapterViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.donation_item, null)
        return DonationsAdapterViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

}