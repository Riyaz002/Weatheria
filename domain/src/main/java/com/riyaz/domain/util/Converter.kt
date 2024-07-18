package com.riyaz.domain.util

import android.graphics.Bitmap
import android.graphics.Canvas
import com.caverock.androidsvg.SVG
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets


object Converter{
    fun convertSvgToBitmap(svgCode: String, width: Int, height: Int): Bitmap? {
        return try {
            val inputStream = ByteArrayInputStream(svgCode.toByteArray(StandardCharsets.UTF_8))
            val svg = SVG.getFromInputStream(inputStream)
            svg.setDocumentWidth(width.toFloat())
            svg.setDocumentHeight(height.toFloat())

            val picture = svg.renderToPicture(width, height)
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawPicture(picture)
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
