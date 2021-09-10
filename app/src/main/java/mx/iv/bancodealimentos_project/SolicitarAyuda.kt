package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import mx.iv.bancodealimentos_project.fragments.CheckboxFragment
import mx.iv.bancodealimentos_project.fragments.MenuFragment
import mx.iv.bancodealimentos_project.fragments.ParaMiFragment

class SolicitarAyuda : AppCompatActivity(), MenuFragment.CallbackMenu, CheckboxFragment.CallbackHelp {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_ayuda)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.askHelpMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transaction.commit()

        val checkboxFragment = CheckboxFragment()
        val transact = supportFragmentManager.beginTransaction()
        transact.add(R.id.askHelpFragmentContainer, checkboxFragment, TAG_FRAGMENT)
        transact.commit()
    }

    override fun returnAct() {
        finish()
    }

    override fun replaceParaMiFragment() {
        var currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        var transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null) {
            transaction.remove(currentFragment)
        }
        val paraMi = ParaMiFragment()
        transaction.add(R.id.askHelpFragmentContainer, paraMi, TAG_FRAGMENT)
        transaction.commit()

    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}