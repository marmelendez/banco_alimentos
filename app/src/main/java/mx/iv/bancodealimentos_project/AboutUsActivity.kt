package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class AboutUsActivity : AppCompatActivity(), MenuFragment.CallbackMenu {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutus)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.aboutUsCvMenuFragmentContainer, menuFragment, TAG_FRAGMENT)

    }

    override fun returnAct(){
        finish()
    }

    companion object{
        private const val TAG_FRAGMENT = "fragment"
    }
}