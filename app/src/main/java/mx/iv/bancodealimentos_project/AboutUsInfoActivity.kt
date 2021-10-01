package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar

import mx.iv.bancodealimentos_project.databinding.ActivityAboutUsInfoBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class AboutUsInfoActivity : AppCompatActivity(), MenuFragment.CallbackMenu {
    private lateinit var binding: ActivityAboutUsInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun returnAct() {
        finish()
    }



    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}


