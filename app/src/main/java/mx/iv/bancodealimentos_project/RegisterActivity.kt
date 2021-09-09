package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import mx.iv.bancodealimentos_project.fragments.LogInFragment
import mx.iv.bancodealimentos_project.fragments.SignInFragment
import mx.iv.bancodealimentos_project.databinding.ActivityRegisterBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class RegisterActivity : AppCompatActivity(), LogInFragment.Callback, MenuFragment.CallbackMenu  {

    private lateinit var binding: ActivityRegisterBinding // Nos permite acceder a las views del layout de la activity
    private lateinit var loginFragment: LogInFragment
    private lateinit var signInFragment: SignInFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FRAGMENTO DE MENU
        // Muestra el fragmento de menu desde el inicio
        val menuFragment = MenuFragment()
        val transactionMenu = supportFragmentManager.beginTransaction()
        transactionMenu.add(R.id.registerMenuFragmentContainer, menuFragment, TAG_FRAGMENT)
        transactionMenu.commit()

        // FRAGMENTO DE INICIO/REGISTRO
        // Declaramos nuestros fragmentos de la clase LogIn y SignIn
        loginFragment = LogInFragment()
        signInFragment = SignInFragment()

        // Primero mostramos el fragmento de inicio de sesión
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.registerFragmentContainer, loginFragment, TAG_FRAGMENT)
        transaction.commit()
    }

    // Funcion para remplazar el fragmento actual por otro (LogIn a SignIn o viceversa)
    private fun replaceFragment() {
        val currentFragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT)
        if(currentFragment != null){
            var newFragment : Fragment = loginFragment

            if (currentFragment == loginFragment)
                newFragment = signInFragment

            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(currentFragment)
            transaction.add(R.id.registerFragmentContainer, newFragment, TAG_FRAGMENT)
            transaction.commit()
        }
    }

    // Implementa metodos de la clase Callback (declarada en Fragments.LogInFragment)
    override fun runLogIn() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun runSignIn() {
        replaceFragment()
    }

    // Implementa metodos de la clase Callbackmenu (declarada en Fragments.MenuFragment)
    override fun returnAct() {
        finish()
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}