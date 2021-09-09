package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.HelpOptionsFragment
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class HelpActivity : AppCompatActivity(), MenuFragment.CallbackMenu  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        // FRAGMENTO DE MENU
        // Muestra el fragmento de menu desde el inicio
        val menuFragment = MenuFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.helpMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()

        // FRAGMENTO DE MENU DE OPCIONES DE AYUDA
        // Muestra el fragmento de opciones de ayuda desde el inicio
        val helpMenuFragment = HelpOptionsFragment()
        val transactionHelpMenu = supportFragmentManager.beginTransaction()
        transactionHelpMenu.add(R.id.helpFragmentContainer, helpMenuFragment, TAG_FRAGMENT)
        transactionHelpMenu.commit()
    }

    override fun returnAct() {
        finish()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}