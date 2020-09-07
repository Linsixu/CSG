package cn.self.csgproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.self.csgproject.databinding.ActivityMainBinding
import cn.self.csgproject.pdf.PdfConvertToBitmap

class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.content.setImageBitmap(PdfConvertToBitmap.initBitmap(this))
        mBinding.next.setOnClickListener {
            mBinding.content.setImageBitmap(PdfConvertToBitmap.nextPage())
        }

        mBinding.clear.setOnClickListener {
            PdfConvertToBitmap.clear()
            mBinding.content.setImageBitmap(null)
        }
    }
}