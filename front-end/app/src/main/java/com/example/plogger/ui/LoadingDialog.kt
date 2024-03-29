package com.example.plogger.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ProgressBar
import com.example.plogger.R


class LoadingDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

        setCancelable(false)

        findViewById<ProgressBar>(R.id.progressBar).indeterminateDrawable.setColorFilter(
            context.resources.getColor(R.color.basic), PorterDuff.Mode.SRC_IN
        )
//        findViewById<ProgressBar>(R.id.progressBar).progressDrawable = context.resources.getDrawable(R.drawable.custom_progress_bar)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}
