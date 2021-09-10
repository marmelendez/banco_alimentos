package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.BeVolunteerFragment
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class BeVolunteerActivity : AppCompatActivity(), MenuFragment.CallbackMenu {
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

    companion object{
        private const val TAG_FRAGMENT = "fragment"
    }
}