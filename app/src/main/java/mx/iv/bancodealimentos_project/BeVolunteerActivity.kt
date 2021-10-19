package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.BeVolunteerFragment
import mx.iv.bancodealimentos_project.fragments.FormResponseFragment
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class BeVolunteerActivity : AppCompatActivity(), MenuFragment.CallbackMenu, BeVolunteerFragment.CallbackVolunteer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_be_volunteer)

        val menuFragment = MenuFragment()
        val transact = supportFragmentManager.beginTransaction()
        transact.add(R.id.beVolunteerMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transact.commit()

        val serVoluntario = BeVolunteerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.beVolunteerOptions, serVoluntario, TAG_FRAGMENT)
        transaction.commit()
    }

    override fun returnAct(){
        finish()
    }

    override fun replaceFormResponseFragment() {
        val currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        val transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null) {
            transaction.remove(currentFragment)
        }
        val formResponse = FormResponseFragment()
        transaction.add(R.id.beVolunteerOptions, formResponse, TAG_FRAGMENT)
        transaction.commit()
    }

    companion object{
        private const val TAG_FRAGMENT= "fragment"
    }
}