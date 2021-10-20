package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.databinding.ActivityBeBeneficiaryBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class BeBeneficiaryActivity : AppCompatActivity(), MenuFragment.CallbackMenu {

    private lateinit var binding: ActivityBeBeneficiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeBeneficiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener para mostrar formulario de ser beneficiario
        binding.beneficiaryBtnBeneficiary.setOnClickListener {
            val intent = Intent(this, AskHelpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun returnAct(){
        finish()
    }
}