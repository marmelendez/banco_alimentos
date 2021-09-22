package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import mx.iv.bancodealimentos_project.R
import mx.iv.bancodealimentos_project.fragments.*

class SolicitarAyuda : AppCompatActivity(), MenuFragment.CallbackMenu, CheckboxFragment.CallbackHelp{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_ayuda)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.MenuSolicitarAyuda, menuFragment, TAG_FRAGMENT)
        transaction.commit()

        val ayudaEs = CheckboxFragment()
        val transact = supportFragmentManager.beginTransaction()
        transact.add(R.id.Options, ayudaEs, TAG_FRAGMENT)
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
        val paraMi = OtherPersonInformation()
        transaction.add(R.id.Options, paraMi, TAG_FRAGMENT)
        transaction.commit()
    }

    override fun Other() {
        var currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        var transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null) {
            transaction.remove(currentFragment)
        }
        val OtherFrag = OtherPerson()
        transaction.add(R.id.Options, OtherFrag, TAG_FRAGMENT)
        transaction.commit()
    }
    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}