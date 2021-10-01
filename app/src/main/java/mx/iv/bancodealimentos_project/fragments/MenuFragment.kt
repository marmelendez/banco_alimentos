package mx.iv.bancodealimentos_project.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import mx.iv.bancodealimentos_project.*
import java.lang.RuntimeException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragmento que muestra el menu en la parte superior de cada actividad
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener : CallbackMenu? = null

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
        // Infla el layout y agrega listeners a botones
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val btnMenu = view.findViewById<ImageButton>(R.id.menuIbMenu)
        val btnReturn = view.findViewById<ImageButton>(R.id.menuIbReturn)

        btnMenu.setOnClickListener {
            val popupMenu = PopupMenu(it.context, it)

            popupMenu.menuInflater.inflate(R.menu.menu_general, popupMenu.menu)
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
                    R.id.menu_account -> {
                        val intent = Intent(it.context, RegisterActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
            popupMenu.show()
        }

        btnReturn.setOnClickListener {
            listener?.returnAct()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = if (context is CallbackMenu) {
            context
        } else {
            throw RuntimeException("Must implement Callback in Activity")
        }
    }

    interface CallbackMenu {
        fun returnAct()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}