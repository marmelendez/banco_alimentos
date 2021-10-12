package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.databinding.ActivityAccountBinding
import mx.iv.bancodealimentos_project.databinding.ActivityDonationsBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class AccountActivity : AppCompatActivity(), MenuFragment.CallbackMenu {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuFragment = MenuFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.accountMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()

        binding.accountBtnLogOut.setOnClickListener {
            logOut()
        }
    }

    private fun logOut() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Estas segurx que quieres cerrar sesión?")

        builder.setNegativeButton(
            "No"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, i ->
            Firebase.auth.signOut()
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }

    override fun returnAct() {
        finish()
    }
}