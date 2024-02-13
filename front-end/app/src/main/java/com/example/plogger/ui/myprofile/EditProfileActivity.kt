package com.example.plogger.ui.myprofile

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.plogger.R
import com.example.plogger.databinding.ActivityEditProfileBinding
import com.example.plogger.ui.LoadingDialog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    private lateinit var selectedImageUri: Uri
//    private val editProfileViewModel: EditProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUi()
    }

    private fun setUi() {
        binding.apply {
            backBtn.setOnClickListener {
                finish()
            }
            editImgTitle.setOnClickListener {
                setPopUpMenu(this.root.context, it)
            }
            editImgBtn.setOnClickListener {
                setPopUpMenu(this.root.context, it)
            }
            editNicknameBtn.setOnClickListener {

                Toast.makeText(this.root.context,"닉네임이 변경되었습니다.",Toast.LENGTH_SHORT).show()
            }
            editPasswordBtn.setOnClickListener {

            }
            editEmailBtn.setOnClickListener {

                Toast.makeText(this.root.context,"이메일이 변경되었습니다.",Toast.LENGTH_SHORT).show()
            }
        }

        val dialog = LoadingDialog(this)
//        editProfileViewModel.isLoading.observe(this) {
//            if (editProfileViewModel.isLoading.value!!) {
//                dialog.show()
//            } else if (!editProfileViewModel.isLoading.value!!) {
//                dialog.dismiss()
//            }
//        }
    }

    private fun setPopUpMenu(context: Context, view: View) {
        val popUp = PopupMenu(context, view)
        popUp.menuInflater.inflate(R.menu.profile_image_edit_menu, popUp.menu)
        popUp.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.image_select -> openGallery()
                R.id.image_modify -> postMyProfileImage()
                R.id.image_remove -> {
                    binding.currentImg.setImageResource(R.drawable.profile_img)
                }
            }
            false
        }
        popUp.show()
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            binding.currentImg.setImageURI(selectedImageUri)
        }
    }

    private fun postMyProfileImage(): MultipartBody.Part {
        val imageFile = File(cacheDir, "temp_image.jpg")
        val outputStream = FileOutputStream(imageFile)
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.close()

        val requestBody = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData("file", imageFile.name, requestBody)
    }
}