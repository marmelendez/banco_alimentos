package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.databinding.ActivityAboutusBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class AboutUsActivity : AppCompatActivity(), MenuFragment.CallbackMenu {

    private lateinit var binding: ActivityAboutusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener del boton de ¿quienes somos?
        binding.aboutUsCDinfo.setOnClickListener {
            val intent = Intent(it.context, AboutUsInfoActivity::class.java)
            intent.putExtra("id", "info")
            startActivity(intent)
        }

        // Listener del boton de datos informativos
        binding.aboutUsCDdata.setOnClickListener {
            val intent = Intent(it.context, AboutUsInfoActivity::class.java)
            intent.putExtra("id", "data")
            startActivity(intent)
        }
    }

    override fun returnAct(){
        finish()
    }
}