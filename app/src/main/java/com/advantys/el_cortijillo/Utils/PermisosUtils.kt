package com.advantys.el_cortijillo.Utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermisosUtils  @Inject constructor() {

    companion object {

        const val REQUEST_STORAGE_PERMISSION = 1
        private val permisosAlmacenamiento = arrayListOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        fun solicitarPermisosAlmacenamiento(activity: Activity) {
            if(Build.VERSION.SDK_INT >= 30) {
                val uri = Uri.parse("package:com.advantys.el_cortijillo")
                activity.startActivity(Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri))
                activity.finish()
            }
            else {
                val permissionsToRequest = mutableListOf<String>()

                for (permmiso in permisosAlmacenamiento) {
                    if (ContextCompat.checkSelfPermission(activity, permmiso) != PackageManager.PERMISSION_GRANTED)
                        permissionsToRequest.add(permmiso)
                }

                if (permissionsToRequest.isNotEmpty())
                    ActivityCompat.requestPermissions(activity, permissionsToRequest.toTypedArray(), REQUEST_STORAGE_PERMISSION)
            }
        }

        fun verificarPermisosAlmacenamiento(activity: Activity): Boolean {
            if(Build.VERSION.SDK_INT >= 30)
                return Environment.isExternalStorageManager()
            else {
                val permisosRequeridos = mutableListOf<String>()

                for (permmiso in permisosAlmacenamiento) {
                    if (ContextCompat.checkSelfPermission(activity, permmiso) != PackageManager.PERMISSION_GRANTED)
                        permisosRequeridos.add(permmiso)
                }

                return !permisosRequeridos.isNotEmpty()
            }
        }

        fun irConfiguracionPermisos(activity: Activity) {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.data = Uri.fromParts("package", activity.packageName, null)
            activity.startActivity(intent)
        }
    }
}