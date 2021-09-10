package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.databinding.ActivityVolunteerBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class VolunteerActivity : AppCompatActivity(), MenuFragment.CallbackMenu {

    private lateinit var binding: ActivityVolunteerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVolunteerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.volunteerMenuFragmentContainer, menuFragment, TAG_FRAGMENT)

        binding.volunteerBtnVolunteer.setOnClickListener {
            val intent = Intent(this, BeVolunteerActivity::class.java)
            startActivity(intent)
        }
    }

    override fun returnAct(){
        finish()
    }

    companion object{
        private const val TAG_FRAGMENT = "fragment"
    }
}