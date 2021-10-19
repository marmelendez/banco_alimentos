package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.databinding.ActivityAccountBinding
import mx.iv.bancodealimentos_project.fragments.MenuAccountFragment

class AccountActivity : AppCompatActivity(){

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuFragment = MenuAccountFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.accountMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()

        val donationsFragment = DonationsListFragment()
        val transactionDonations = supportFragmentManager.beginTransaction()
        transactionDonations.add(R.id.accountFcContainer, donationsFragment, TAG_FRAGMENT)
        transactionDonations.commit()

       /* donationsList = mutableListOf()
        getDonationsList()
        recyclerList = binding.accountRvList
        recyclerList.layoutManager = LinearLayoutManager(this)
        val favoritesAdapter = DonationsAdapter(donationsList as ArrayList<DonationData>)
        recyclerList.adapter = favoritesAdapter*/

        if (!checkCurrentUser()) {
            binding.accountTitle.text = "Para ingresar a este apartado es necesario que inicies sesión primero"
            binding.accountTitle.textSize = 10f
        }
    }

    private fun checkCurrentUser(): Boolean {
        if (Firebase.auth.currentUser != null) {
            return true
        }
        return false
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}