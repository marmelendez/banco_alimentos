package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class DonationsActivity : AppCompatActivity(),  MenuFragment.CallbackMenu  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)

        // FRAGMENTO DE MENU
        // Muestra el fragmento de menu desde el inicio
        val menuFragment = MenuFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.donationsMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()
    }

    override fun returnAct() {
        finish()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}