package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

class MainActivity : AppCompatActivity() {
//    private val onBoardingSliderAdapter = OnBoardingSlideAdapter(
//        listOf(
//            OnBoardingSlide(
//                "Welcome to Eshi Blood",
//                "Where you can save life by donating your blood",
//                R.drawable.slide1
//            ),
//            OnBoardingSlide(
//                "Donation Donation",
//                "Be a hero by saving lifes",
//                R.drawable.slide2
//            ),
//            OnBoardingSlide(
//                "One More Step",
//                "You can appoint in no time and donate anytime",
//                R.drawable.slide3
//            )
//        )
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav:BottomNavigationView = findViewById(R.id.bottom_navigation)
        val btmSht = BottomSheetDialog(this)
        val view =  layoutInflater.inflate(R.layout.bottom_sheet_layout,null)
        val closeBtn = view.findViewById<TextView>(R.id.closeBottomSheetBtn)
        val qrCardImage = view.findViewById<ImageView>(R.id.qr_code)
        val bitmap: Bitmap= generateQRCode("hello world")
        if (bitmap == null){
            Log.d("eeeeeeeeeeeeeeeeeee","bitmap")
        }
        if(qrCardImage==null){
            Log.d("eeeeeeeeeeeeeeeeeee","image view")
        }
        else{
            qrCardImage.setImageBitmap(bitmap)
        }

        closeBtn.setOnClickListener {

            btmSht.dismiss()
        }
        var navListener =
            BottomNavigationView.OnNavigationItemSelectedListener{item: MenuItem ->
                Log.d("onNavigationItemSelect","passed")
                var selectedFragment: Fragment? = null



                when(item.itemId){
                    R.id.bottom_navigation_home->selectedFragment=home_fragment()
                    R.id.bottom_navigation_appointment->selectedFragment=AppointmentFragment()
//                    R.id.bottom_navigation_donor_card->selectedFragment=DonorCardFragment()
                    R.id.bottom_navigation_donor_card -> {

                        btmSht.setContentView(view)
                        btmSht.show()
                    }

                    R.id.bottom_navigation_about_donation -> selectedFragment =
                        AboutDonationFragment()

                }
                if (selectedFragment != null) {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,selectedFragment).commit()
                }
                return@OnNavigationItemSelectedListener true
            }
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,home_fragment()).commit()
    }


    private fun generateQRCode(text: String): Bitmap {
        val width = 500
        val height = 500
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val codeWriter = MultiFormatWriter()
        try {
            val bitMatrix = codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
            Log.d("D", "generateQRCode: ${e.message}")
        }
        return bitmap
    }

}