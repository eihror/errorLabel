package com.eihror.errorlabel

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class ErrorLabel : LinearLayout {

    lateinit var mError: LinearLayout
    lateinit var mErrorView: TextView
    lateinit var mImageView: ImageView

    private var textColor: Int = android.R.color.holo_red_dark
    private var textSize: Float = 12F

    constructor(context: Context) : super(context){
        initErrorView(context)
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        initErrorView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initErrorView(context)
    }

    private fun initErrorView(context: Context?){
        orientation = LinearLayout.VERTICAL
        setWillNotDraw(false)
        setAddStatesFromChildren(true)

        //Set LinearLayout error
        this.mError = LinearLayout(context)
        //Set TextView error
        this.mErrorView = TextView(context)
        //Set ImageView error
        this.mImageView = ImageView(context)

        this.mError.orientation = LinearLayout.HORIZONTAL
        this.mError.setWillNotDraw(false)
        this.mError.setAddStatesFromChildren(true)
        this.mError.textAlignment = View.TEXT_ALIGNMENT_CENTER
        this.mError.gravity = Gravity.CENTER or Gravity.CENTER_VERTICAL
        this.mError.visibility = View.GONE
        this.mError.setPadding(0, 5, 0, 0)

        mImageView.scaleType = ImageView.ScaleType.FIT_XY

        this.mError.addView(this.mErrorView, childCount)
        this.mError.addView(this.mImageView, childCount)

    }

    fun setIcon(value: Int?){
        if(value != null){
            val sdk = android.os.Build.VERSION.SDK_INT
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mImageView.setBackgroundDrawable(ContextCompat.getDrawable(context, value))
            } else {
                mImageView.background = ContextCompat.getDrawable(context, value)
            }
        }else{
            mImageView.visibility = View.GONE
        }
    }

    fun setTextColor(color: Int?){
        if(color != null){
            mErrorView.setTextColor(ContextCompat.getColor(context!!, color))
        }else{
            mErrorView.setTextColor(ContextCompat.getColor(context!!, this.textColor))
        }
    }

    fun setTextSize(size: Float?){
        if(size != null && size > this.textSize){
            mErrorView.textSize = size
        }else{
            mErrorView.textSize = this.textSize
        }
    }

    fun setFont(font: String?) {
        if (!font.isNullOrEmpty()) {
            val face = Typeface.createFromAsset(context.assets, font)
            if (face != null) {
                mErrorView.typeface = face
            }
        }
    }

    fun setError(message: String?) {
        if (message!!.isNotEmpty()) {
            mErrorView.text = message
        } else {
            mErrorView.text = ""
        }
    }

    fun setErrorEnabled(b: Boolean) {
        var wasAdded: Boolean = false
        if (b) {
            if (!wasAdded) {
                removeView(this.mError)
                addView(this.mError, childCount)
                wasAdded = true
            }
            mError.visibility = View.VISIBLE
        } else {
            if (!wasAdded) { return }
            if (mError.visibility == View.GONE) { return }
            mError.visibility = View.GONE
        }
    }
}