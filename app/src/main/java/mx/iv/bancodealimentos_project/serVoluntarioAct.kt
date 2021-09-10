package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.BeeVoluntario
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class serVoluntarioAct : AppCompatActivity(), MenuFragment.CallbackMenu {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ser_voluntario)

        val serVoluntario = BeeVoluntario()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.SerVoluntariosOptions, serVoluntario, TAG_FRAGMENT)


        val menuFragment = MenuFragment()
        val transact = supportFragmentManager.beginTransaction()
        transact.add(R.id.MenuSerVoluntarios, menuFragment, TAG_FRAGMENT)
    }

    override fun returnAct(){
        finish()
    }

    companion object{
        private const val TAG_FRAGMENT = "fragment"
    }
}