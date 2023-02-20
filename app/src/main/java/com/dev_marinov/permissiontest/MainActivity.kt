package com.dev_marinov.permissiontest


import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.dev_marinov.permissiontest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("4444", " MainActivity loaded")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val permissions: List<String> = arrayListOf(
           // android.Manifest.permission.ACCESS_COARSE_LOCATION,
           // android.Manifest.permission.ACCESS_FINE_LOCATION,

            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )


        binding.bt.setOnClickListener {

            // запросить разрешение у пользователя
            ActivityCompat.requestPermissions(this, permissions.toTypedArray(), PackageManager.PERMISSION_GRANTED)


//            if (checkPermission(android.Manifest.permission.CAMERA)) {
//                binding.tv.visibility = View.VISIBLE
//            } else {
//                if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
//                    AlertDialog.Builder(this)
//                        .setMessage("Требуется разрешение камеры для захвата изображения. Предоставьте разрешение на доступ к вашей камере.")
//                        .setPositiveButton("OK") { dialogInterface, i ->
//                            dialogInterface.dismiss()
//                            ActivityCompat.requestPermissions(
//                                this,
//                                arrayOf(android.Manifest.permission.CAMERA),
//                                201
//                            )
//                        }
//                        .setNegativeButton("Cancel") { dialogInterface, i ->
//                            dialogInterface.dismiss()
//                        }
//                        .create()
//                        .show();
//                } else {
//                    ActivityCompat.requestPermissions(
//                        this,
//                        arrayOf(
//                            android.Manifest.permission.CAMERA,
//                            android.Manifest.permission.READ_EXTERNAL_STORAGE
//                        ),
//                        201
//                    )
//                }
//            }
        }
    }


    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

      //  Log.d("4444", " permissions=" + permissions.size + " grantResults=" + grantResults.size )
       // if (requestCode == 201) {
        Log.d("4444", " camera=" + grantResults[0].toString())
        Log.d("4444", " read=" + grantResults[1].toString())
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, " camera Permission granted", Toast.LENGTH_SHORT).show()
                //openCamera()
                binding.tvcamera.visibility = View.VISIBLE
                  Log.d("4444", " camera=" + (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
            } else {
                Toast.makeText(this, "camera Permission denied", Toast.LENGTH_SHORT).show()
            }
            if (grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, " read Permission granted", Toast.LENGTH_SHORT).show()
                //openCamera()
                binding.tvread.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, " read Permission denied", Toast.LENGTH_SHORT).show()
            }
      //  }
    }
}