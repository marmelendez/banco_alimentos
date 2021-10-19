package mx.iv.bancodealimentos_project.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.*

class MenuAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_menu_account, container, false)

        val btnMenu = view.findViewById<ImageButton>(R.id.menuAccountIbMenu)

        btnMenu.setOnClickListener {
            val popupMenu = PopupMenu(it.context, it)

            popupMenu.menuInflater.inflate(R.menu.menu_account, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_home -> {
                        val intent = Intent(it.context, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_about -> {
                        val intent = Intent(it.context, AboutUsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_help -> {
                        val intent = Intent(it.context, HelpActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_donations -> {
                        val intent = Intent(it.context, DonationsActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_log_out -> {
                        logOut()
                    }
                }
                true
            }
            popupMenu.show()
        }

        return view
    }

    private fun logOut() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Estas segurx que quieres cerrar sesión?")

        builder.setNegativeButton(
            "No"
        ) { _, _ -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, _ ->
            Firebase.auth.signOut()
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}