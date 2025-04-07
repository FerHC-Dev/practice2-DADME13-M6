package diplomado.practice2.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import diplomado.practice2.R
import diplomado.practice2.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val splashScreen = installSplashScreen()
        //splashScreen.setKeepOnScreenCondition { true }
        message(getString(R.string.txtConnected))
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
            val icon = findViewById<ImageView>(R.id.ivIcon)
            val fadeInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.animated_icon)
            icon.startAnimation(fadeInAnimation)
            Handler(Looper.getMainLooper()).postDelayed({
                //splashScreen.setKeepOnScreenCondition { false }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000L)
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun message(text : String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
            .setTextColor(getColor(R.color.white))
            .setBackgroundTint(getColor(R.color.black))
            .show()
    }


}