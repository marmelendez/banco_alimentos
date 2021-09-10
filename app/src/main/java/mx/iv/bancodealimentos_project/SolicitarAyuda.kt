package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.iv.bancodealimentos_project.R
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class SolicitarAyuda : AppCompatActivity(), MenuFragment.CallbackMenu {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_ayuda)

        val menuFragment = MenuFragment();
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.MenuSolicitarAyuda, menuFragment, TAG_FRAGMENT)
    }

    override fun returnAct() {
        finish()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}