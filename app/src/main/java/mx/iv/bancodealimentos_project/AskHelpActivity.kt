package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.*

class AskHelpActivity : AppCompatActivity(), MenuFragment.CallbackMenu, CheckboxFragment.CallbackHelp{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_help)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.askHelpMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transaction.commit()

        val checkbox = CheckboxFragment()
        val transact = supportFragmentManager.beginTransaction()
        transact.add(R.id.askHelpFragmentContainer, checkbox, TAG_FRAGMENT)
        transact.commit()
    }

    override fun returnAct() {
        finish()
    }


    override fun replaceExternalHelpFragment() {
        var currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        var transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null) {
            transaction.remove(currentFragment)
        }
        val paraMi = ExternalHelpFragment()
        transaction.add(R.id.askHelpFragmentContainer, paraMi, TAG_FRAGMENT)
        transaction.commit()
    }

    override fun replacePersonalHelpFragment() {
        var currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        var transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null) {
            transaction.remove(currentFragment)
        }
        val otherFrag = PersonalHelpFragment()
        transaction.add(R.id.askHelpFragmentContainer, otherFrag, TAG_FRAGMENT)
        transaction.commit()
    }

    override fun replaceResponseFragment() {
        val currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        val transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null) {
            transaction.remove(currentFragment)
        }
        val formResponse = FormResponseFragment()
        transaction.add(R.id.askHelpFragmentContainer, formResponse, TAG_FRAGMENT)
        transaction.commit()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}