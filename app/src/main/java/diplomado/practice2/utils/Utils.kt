package diplomado.practice2.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
    return networkCapabilities != null && networkCapabilities.hasCapability(
        NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
/*
private fun message(text : String){
    //Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
        .setTextColor(getColor(R.color.white))
        .setBackgroundTint(getColor(R.color.black))
        .show()
}
*/