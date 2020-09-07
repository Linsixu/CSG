package cn.self.csgproject.pdf

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor

/**
 * User: linsixu@yy.com
 * Date: 2020/9/7
 * Desc: 描述
 * SinceVer: TODO:哪个版本添加的
 */
object PdfConvertToBitmap {
    var currentIndex: Int = 0
    private var mFileDescriptor: ParcelFileDescriptor? = null
    private var mPdfRenderer: PdfRenderer? = null

    fun initBitmap(context: Context): Bitmap? {
        mFileDescriptor =
            context.applicationContext.assets.openFd("sample.pdf").parcelFileDescriptor
        mPdfRenderer = PdfRenderer(mFileDescriptor!!)
        return createBitmap(0)
    }

    fun nextPage(): Bitmap? {
        mPdfRenderer?.let {
            currentIndex++
            val count = it.pageCount
            currentIndex %= count
            return createBitmap(currentIndex)
        }
        return null
    }

    fun clear() {
        mFileDescriptor?.close()
        mPdfRenderer?.close()

        mFileDescriptor = null
        mPdfRenderer = null
    }

    private fun createBitmap(index: Int): Bitmap? {
        mPdfRenderer?.let { render ->
            val mCurrentPage = render.openPage(currentIndex)
            val bitmap = Bitmap.createBitmap(
                mCurrentPage.width,
                mCurrentPage.height,
                Bitmap.Config.ARGB_8888
            )
            mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT)
            mCurrentPage.close()
            return bitmap
        }
        return null
    }
}