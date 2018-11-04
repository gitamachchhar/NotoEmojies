package com.smiley.gita.notocoloremojies

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.support.text.emoji.widget.EmojiAppCompatEditText
import android.support.text.emoji.widget.EmojiAppCompatTextView
import android.support.v4.provider.FontRequest
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.smiley.gita.notocoloremojies.adapter.EmojiPagerAdapter
import com.smiley.gita.notocoloremojies.emojies.*
import com.smiley.gita.notocoloremojies.fragments.EmojiFragment

class MainActivity : AppCompatActivity(), EmojiFragment.OnEmojiconClickedListener {

    val USE_BUNDLED_EMOJI = true
    var fragmentList: ArrayList<EmojiFragment>? = null
    var emoji_text: EmojiAppCompatTextView?= null
    var emoji_edit_text: EmojiAppCompatEditText?= null
    var icons: IntArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEmojiCompat()
        setContentView(R.layout.activity_main)
        setupViewPager()

        emoji_text = findViewById<EmojiAppCompatTextView>(R.id.emoji_text)
        emoji_edit_text = findViewById<EmojiAppCompatEditText>(R.id.emoji_edit_text)

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

        val tabs_main = findViewById<TabLayout>(R.id.tabs_main)
        tabs_main.setupWithViewPager(viewPager)


        icons = intArrayOf(R.drawable.ic_emoji_people_light,
                R.drawable.ic_emoji_nature_light,
                R.drawable.ic_emoji_people_light,
                R.drawable.ic_emoji_people_light,
                R.drawable.ic_emoji_objects_light,
                R.drawable.ic_emoji_symbols_light,
                R.drawable.ic_emoji_places_light,
                R.drawable.ic_emoji_people_light)

        for (i in 0 until tabs_main.getTabCount()) {
            tabs_main.getTabAt(i)!!.setIcon(icons!![i])
        }

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
                        Log.e("", "initialization failed", throwable)
                    }
                })

        EmojiCompat.init(config)
    }

    override fun onEmojiconClicked(emoji: String) {
        var strText = emoji_text?.text
        emoji_text?.text = "$strText$emoji"
        emoji_edit_text?.setText("$strText$emoji")
    }

}
