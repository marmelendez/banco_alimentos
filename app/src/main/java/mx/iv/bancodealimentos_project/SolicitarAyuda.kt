package mx.iv.bancodealimentos_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
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

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.MeCheckBox -> {
                    if (checked) {
                        Toast.makeText(this, "Me picado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Me despicado", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.ElseCheckBox -> {
                    if (checked) {
                        Toast.makeText(this, "Else picado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Else despicado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}