package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.databinding.ActivityAccountBinding
import mx.iv.bancodealimentos_project.fragments.DonationsListFragment

class AccountActivity : AppCompatActivity(){

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mostrar lista de donaciones del usuario
        val donationsFragment = DonationsListFragment()
        val transactionDonations = supportFragmentManager.beginTransaction()
        transactionDonations.add(R.id.accountFcContainer, donationsFragment, TAG_FRAGMENT)
        transactionDonations.commit()

        // Checar si hay un usuario registrado o no
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