package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.databinding.ActivityAccountBinding
import mx.iv.bancodealimentos_project.databinding.ActivityDonationsBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuFragment = MenuFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.accountMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()

        binding.accountBtnLogOut.setOnClickListener {

        }
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}