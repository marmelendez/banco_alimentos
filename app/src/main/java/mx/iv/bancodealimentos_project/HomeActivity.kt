package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.iv.bancodealimentos_project.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(){

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BOTON SOBRE EL BANCO
        binding.homeCvAbout.setOnClickListener {
            Toast.makeText(this, "Pronto contaremos con este apartado",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        // BOTON COMO AYUDAR
        binding.homeCvHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        // BOTON MI CUENTA
        // Al dar clic al boton de Mi cuenta te redirige a la pagina de iniciar sesión
        binding.homeCvAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}