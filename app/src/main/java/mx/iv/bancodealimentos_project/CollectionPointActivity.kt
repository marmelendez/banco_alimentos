package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.iv.bancodealimentos_project.databinding.ActivityCollectionPointBinding
import mx.iv.bancodealimentos_project.databinding.ActivityDonationsBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class CollectionPointActivity : AppCompatActivity(), MenuFragment.CallbackMenu{

    private lateinit var binding: ActivityCollectionPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionPointBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val menuFragment = MenuFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.collectionMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transaction.commit()

        /*binding.collectionBtnAsk.setOnClickListener {
            Toast.makeText(this, "Pronto contaremos con este apartado", Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun returnAct() {
        finish()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}