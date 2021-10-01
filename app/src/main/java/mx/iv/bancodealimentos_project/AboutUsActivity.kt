package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.iv.bancodealimentos_project.databinding.ActivityAboutusBinding
import mx.iv.bancodealimentos_project.databinding.ActivityHomeBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class AboutUsActivity : AppCompatActivity(), MenuFragment.CallbackMenu {

    private lateinit var binding: ActivityAboutusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.aboutUsCvMenuFragmentContainer, menuFragment, TAG_FRAGMENT)

        binding.aboutUsCDinfo.setOnClickListener {
            Toast.makeText(this, "Pronto contaremos con este apartado", Toast.LENGTH_SHORT).show()
            /*val intent = Intent(it.context, AboutUsInfoActivity::class.java)
            startActivity(intent)*/
        }

        binding.aboutUsCDdata.setOnClickListener {
            Toast.makeText(this, "Pronto contaremos con este apartado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun returnAct(){
        finish()
    }

    companion object{
        private const val TAG_FRAGMENT = "fragment"
    }
}