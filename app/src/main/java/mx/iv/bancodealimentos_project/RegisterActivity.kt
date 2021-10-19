package mx.iv.bancodealimentos_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import mx.iv.bancodealimentos_project.fragments.LogInFragment
import mx.iv.bancodealimentos_project.fragments.SignInFragment
import mx.iv.bancodealimentos_project.databinding.ActivityRegisterBinding
import mx.iv.bancodealimentos_project.fragments.MenuFragment

class RegisterActivity : AppCompatActivity(), LogInFragment.Callback, MenuFragment.CallbackMenu  {

    private lateinit var binding: ActivityRegisterBinding // Nos permite acceder a las views del layout de la activity
    private lateinit var loginFragment: LogInFragment
    private lateinit var signInFragment: SignInFragment
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

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
    override fun runLogIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("FIREBASE", "Login exitoso")
                    val intent = Intent(this, AccountActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun runSignIn(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("FIREBASE", "Registro exitoso")
                    db.collection("users").document(email).set(
                        hashMapOf("email" to email)
                    )
                    val intent = Intent(this, AccountActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        if (Firebase.auth.currentUser != null) {
            Toast.makeText(this, "Bienvenido de nuevo ${Firebase.auth.currentUser?.email}",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }

    override fun replaceFrag() {
        replaceFragment()
    }

    // Implementa metodos de la clase Callbackmenu (declarada en Fragments.MenuFragment)
    override fun returnAct() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val TAG_FRAGMENT = "fragment"
    }
}