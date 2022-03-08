package com.demon.ara

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.demon.ara.databinding.ActivityActResultBinding
import com.demon.core.DeMonActivityResult
import com.demon.qfsolution.utils.MimeType
import com.demon.qfsolution.utils.getExternalOrFilesDirPath
import com.demon.qfsolution.utils.getFileUri
import com.demon.qfsolution.utils.isFileExists
import java.io.File

class ActResultActivity : AppCompatActivity() {
    private val TAG = "ActResultActivity"

    private lateinit var binding: ActivityActResultBinding

    private val requestPermission = DeMonActivityResult(this, ActivityResultContracts.RequestPermission())
    private val requestMultiplePermissions = DeMonActivityResult(this, ActivityResultContracts.RequestMultiplePermissions())
    private val takePicture = DeMonActivityResult(this, ActivityResultContracts.TakePicture())
    private val takePicturePreview = DeMonActivityResult(this, ActivityResultContracts.TakePicturePreview())
    private val captureVideo = DeMonActivityResult(this, ActivityResultContracts.CaptureVideo())
    private val pickContact = DeMonActivityResult(this, ActivityResultContracts.PickContact())
    private val getContent = DeMonActivityResult(this, ActivityResultContracts.GetContent())
    private val getMultipleContents = DeMonActivityResult(this, ActivityResultContracts.GetMultipleContents())
    private val openDocument = DeMonActivityResult(this, ActivityResultContracts.OpenDocument())
    private val openMultipleDocuments = DeMonActivityResult(this, ActivityResultContracts.OpenMultipleDocuments())
    private val createDocument = DeMonActivityResult(this, ActivityResultContracts.CreateDocument())
    private val openDocumentTree = DeMonActivityResult(this, ActivityResultContracts.OpenDocumentTree())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {
            btn1.setOnClickListener {
                requestPermission.launch(Manifest.permission.CAMERA) {
                    Log.i(TAG, "requestPermission: $it")
                }
            }

            btn2.setOnClickListener {
                requestMultiplePermissions.launch(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Log.i(TAG, "requestMultiplePermissions: $it")
                }
            }

            btn3.setOnClickListener {
                val savePath = "${getExternalOrFilesDirPath(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
                val uri = File(savePath).getFileUri()
                takePicture.launch(uri) {
                    Log.i(TAG, "takePicture: $it")
                }
            }

            btn4.setOnClickListener {
                takePicturePreview.launch(null) {
                    Log.i(TAG, "takePicturePreview: $it")
                }
            }

            btn5.setOnClickListener {
                getContent.launch(MimeType.all) {
                    Log.i(TAG, "getContent: $it  ${it.isFileExists()}")
                }
            }

            btn6.setOnClickListener {
                getMultipleContents.launch(MimeType.all) {
                    Log.i(TAG, "getMultipleContents: $it")
                }
            }

            btn7.setOnClickListener {
                openDocument.launch(arrayOf(MimeType.all)) {
                    Log.i(TAG, "openDocument: $it")
                }
            }

            btn8.setOnClickListener {
                openMultipleDocuments.launch(arrayOf(MimeType.all)) {
                    Log.i(TAG, "openMultipleDocuments: $it")
                }
            }

            btn9.setOnClickListener {
                val uri = getExternalOrFilesDirPath(null).toUri()
                openDocumentTree.launch(uri) {
                    Log.i(TAG, "openDocumentTree: $it")
                }
            }

            btn10.setOnClickListener {
                createDocument.launch("${System.currentTimeMillis()}.txt") {
                    Log.i(TAG, "createDocument: $it")
                }
            }

            btn11.setOnClickListener {
                val savePath = "${getExternalOrFilesDirPath(Environment.DIRECTORY_DCIM)}/${System.currentTimeMillis()}.mp4"
                val uri = File(savePath).getFileUri()
                captureVideo.launch(uri) {
                    Log.i(TAG, "captureVideo: $it")
                }
            }

            btn12.setOnClickListener {
                pickContact.launch(null) {
                    Log.i(TAG, "pickContact: $it")
                }
            }
        }

    }
}