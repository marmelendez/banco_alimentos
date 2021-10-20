package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.databinding.ActivityAboutUsInfoBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment
import mx.iv.bancodealimentos_project.fragments.AboutUsDataFragment


class AboutUsInfoActivity : AppCompatActivity(), MenuFragment.CallbackMenu {
    private lateinit var binding: ActivityAboutUsInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener extra para ver cual fragmento mostrar
        val extra = intent.extras
        var id = "info"
        if (extra != null) {
            id = extra.getString("id").toString()
        }

        // Mostrar fragmento de sliders
        val aboutUsFragment = AboutUsDataFragment(id)
        val transaction2 = supportFragmentManager.beginTransaction()
        transaction2.add(R.id.aboutUsInfoCvAboutUsFragmentContainer, aboutUsFragment, TAG_FRAGMENT)
        transaction2.commit()
    }

    override fun returnAct() {
        finish()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}


