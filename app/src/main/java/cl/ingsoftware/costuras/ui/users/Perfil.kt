package cl.ingsoftware.costuras.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cl.ingsoftware.costuras.R

class Perfil : AppCompatActivity() {

    private val perfilViewModel : PerfilViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)


    }
}