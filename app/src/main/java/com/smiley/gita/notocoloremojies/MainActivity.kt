package com.smiley.gita.notocoloremojies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.util.Log
import android.widget.TextView
import java.lang.ref.WeakReference
import android.support.v4.provider.FontRequest

class MainActivity : AppCompatActivity() {

    val USE_BUNDLED_EMOJI = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEmojiCompat()

        setContentView(R.layout.activity_main)



    }


    private fun initEmojiCompat() {

        val config: EmojiCompat.Config
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = BundledEmojiCompatConfig(applicationContext)
        } else {
            // Use a downloadable font for EmojiCompat
            val fontRequest = FontRequest(
                    "com.google.android.gms.fonts",
                    "com.google.android.gms",
                    "Noto Color Emoji Compat",
                    R.array.com_google_android_gms_fonts_certs)
            config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
        }

        config.setReplaceAll(true)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                        Log.i("", "EmojiCompat initialized")
                    }

                    override fun onFailed(throwable: Throwable?) {
                        Log.e("", "EmojiCompat initialization failed", throwable)
                    }
                })

        EmojiCompat.init(config)
    }

    private class InitCallback internal constructor(regularTextView: TextView) : EmojiCompat.InitCallback() {

        private val mRegularTextViewRef: WeakReference<TextView>

        init {
            mRegularTextViewRef = WeakReference(regularTextView)
        }

        override fun onInitialized() {
            val regularTextView = mRegularTextViewRef.get()
            if (regularTextView != null) {
                val compat = EmojiCompat.get()
                val context = regularTextView.context
//                regularTextView.text = compat.process(context.getString(R.string.emoji_text, EMOJI))
            }
        }

    }

}
