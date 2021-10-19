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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuAccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        val builder = AlertDialog.Builder(context!!)
        builder.setCancelable(true)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Estas segurx que quieres cerrar sesión?")

        builder.setNegativeButton(
            "No"
        ) { dialog, which -> }

        builder.setPositiveButton(
            "Si"
        ) { dialogInterface, i ->
            Firebase.auth.signOut()
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
            dialogInterface.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}