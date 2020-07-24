package com.example.catapplication

import android.Manifest
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.format.Time
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.core.graphics.drawable.toBitmap
import coil.Coil
import coil.api.load
import coil.request.GetRequest
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class DetailedActivity : AppCompatActivity() {
    private val url = "url"
    private val REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val url = intent.getStringExtra(url)
        // Library EasyFlipView is used for this imageView
        val imageView = findViewById<ImageView>(R.id.full_image)
        imageView.load(url) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        val backButton = findViewById<Button>(R.id.back)
        backButton.setOnClickListener {
            onBackPressed()
        }
        val saveButton = findViewById<Button>(R.id.save)
        saveButton.setOnClickListener {
            savePicture(url)
        }
    }

    // This method requests permission and save the picture in the Gallery
    private fun savePicture(url: String) {
        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PermissionChecker.PERMISSION_GRANTED
        ) { ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
        }
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            val time = Time()
            time.setToNow()
            val storage = File(
                cacheDir,
                Integer.toString(time.year) + Integer.toString(time.month + 1)
                    + Integer.toString(time.monthDay) + Integer.toString(time.hour)
                    + Integer.toString(time.minute) + Integer.toString(time.second) + ".jpg"
            )
            val fOut = FileOutputStream(storage)
            val request = GetRequest.Builder(this).data(url).build()
            val imageLoader = Coil.imageLoader(this)
            MainScope().launch {
                val drawable = imageLoader.execute(request).drawable
                val bitmap = drawable?.toBitmap(800, 600)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 80, fOut)
                fOut.flush()
                fOut.close()
                MediaStore.Images.Media.insertImage(
                    contentResolver,
                    storage.absolutePath,
                    storage.name,
                    storage.name
                )
            }
            Toast.makeText(this, "Saved in Gallery", Toast.LENGTH_SHORT).show()
        }
    }
}
