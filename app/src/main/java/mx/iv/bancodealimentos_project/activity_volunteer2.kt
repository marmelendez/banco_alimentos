package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class activity_volunteer2 : AppCompatActivity(), MenuFragment.CallbackMenu {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volunteer2)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.MenuVoluntarios, menuFragment, activity_volunteer2.TAG_FRAGMENT)
    }

    override fun returnAct(){
        finish()
    }

    companion object{
        private const val TAG_FRAGMENT = "fragment"
    }

    fun onClick(view : View){
        val intent = Intent(this, serVoluntarioAct::class.java)
        startActivity(intent)
    }
}