package cl.ingsoftware.costuras.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cl.ingsoftware.costuras.MainActivity
import cl.ingsoftware.costuras.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        lateinit var auth: FirebaseAuth
        auth = Firebase.auth
        setup(auth)
    }
    private fun setup(auth: FirebaseAuth) {
        val boton_registro = findViewById<View>(R.id.boton_registro) as Button
        val email = findViewById<View>(R.id.etEmail) as EditText
        val clave = findViewById<View>(R.id.etPass) as EditText
        val claverep = findViewById<View>(R.id.etRPass) as EditText
        val error = findViewById<View>(R.id.errorReg) as TextView
        boton_registro.setOnClickListener {
            if (email.text.isNotEmpty() && clave.text.isNotEmpty() && claverep.text.isNotEmpty()){
                if(email.text.toString().count { it == '@'}==1 && email.text.toString().count { it == '.'}>=1) {
                    if (clave.text.toString() == claverep.text.toString()) {
                        if (clave.text.toString().length >= 8) {
                            if (isValidPassword(clave.text.toString())) {
                                auth.createUserWithEmailAndPassword(
                                    email.text.toString(),
                                    clave.text.toString()
                                ).addOnCompleteListener(this) {
                                    if (it.isSuccessful) {
                                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                                        Toast.makeText(this, "Registrado con exito", Toast.LENGTH_SHORT).show()
                                        finish()
                                    } else {
                                        showAlert()
                                    }
                                }
                            } else {
                                error.text = "Tu contraseña es debil, usa almenos: 1 minuscula, 1 mayuscula y 1 numero"
                            }
                        }else{
                            error.text = "La contraseña debe tener mas de 8 caracteres"
                        }
                    }else{
                        error.text = "No coinciden las contraseñas"
                    }
                }else{
                    error.text = "El email no es valido"
                }
            }else{
                error.text = "Debes llenar todos los campos"
            }
        }
    }

    fun isValidPassword(password: String): Boolean {
        var upper = 0
        var lower = 0
        var num = 0
        for (letra in password){
            if (letra.isDigit()){
                num++
            }
            if (letra.isUpperCase()){
                upper++
            }
            if (letra.isLowerCase()){
                lower++
            }
        }
        return upper>=1 && lower>=1 && num>=1
    }
    private fun showAlert() {
        val alertbuilder = AlertDialog.Builder(this@Registro)
        alertbuilder.setTitle("Error")
        alertbuilder.setMessage("Error al autenticar usuario")
        alertbuilder.setPositiveButton(
            "OK"
        ) { dialogInterface, i -> finish() }
        val dialog = alertbuilder.show()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }
}

//a