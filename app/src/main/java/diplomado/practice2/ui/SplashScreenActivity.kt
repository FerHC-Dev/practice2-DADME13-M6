package diplomado.practice2.ui

import android.annotation.SuppressLint
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import diplomado.practice2.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val splashScreen = installSplashScreen()
        //splashScreen.setKeepOnScreenCondition { true }

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
}