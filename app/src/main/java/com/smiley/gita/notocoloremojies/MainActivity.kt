package com.smiley.gita.notocoloremojies

import android.os.Bundle
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.support.v4.provider.FontRequest
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.smiley.gita.notocoloremojies.adapter.EmojiPagerAdapter
import com.smiley.gita.notocoloremojies.emojies.*
import com.smiley.gita.notocoloremojies.fragments.EmojiFragment
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    val USE_BUNDLED_EMOJI = true
    var fragmentList: ArrayList<EmojiFragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEmojiCompat()
        setContentView(R.layout.activity_main)
        setupViewPager()

       /* var emoji_text = findViewById<EmojiAppCompatTextView>(R.id.emoji_text)

        var sb: StringBuffer? = null
        sb?.append(Character.toChars(127467))
        sb?.append(Character.toChars(127467))


        val codepoints = intArrayOf(0x1F1EB, 0x1F1F7)
        val s = String(codepoints, 0, codepoints.size)

        emoji_text.text = s.toString()*/
    }

    private fun setupViewPager() {

        fragmentList = ArrayList()
        fragmentList?.add(EmojiFragment().newInstance(SmileyList.getPeopleSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(AnimalNature.getNatureSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(Activities.getActivitiesSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(FoodDrink.getFoodDrinkSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(Object.getObjectsSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(Symbols.getSymbolsSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(TravelsPlaces.getTravelSmiley()!!))
        fragmentList?.add(EmojiFragment().newInstance(Flags.getFlagSmiley()!!))

        val viewPager = findViewById<ViewPager>(R.id.container);
        viewPager.adapter = EmojiPagerAdapter(supportFragmentManager, fragmentList!!)

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
