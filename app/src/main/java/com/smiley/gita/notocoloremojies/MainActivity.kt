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

class MainActivity : AppCompatActivity(), EmojiFragment.OnEmojiconClickedListener, ViewPager.OnPageChangeListener {

    private val USEBUNDLEDEMOJI = true
    private var fragmentList: ArrayList<EmojiFragment>? = null
    private var mTvEmojiText: EmojiAppCompatTextView?= null
    private var mEdEmojiEdit: EmojiAppCompatEditText?= null
    private var icons: IntArray? = null
    private var mRecentEmojiList: ArrayList<String> = ArrayList()
    private var viewPager: ViewPager? = null
    private var emojiAdapter: EmojiPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEmojiCompat()
        setContentView(R.layout.activity_main)
        setupViewPager()

        mTvEmojiText = findViewById(R.id.emoji_text)
        mEdEmojiEdit = findViewById(R.id.emoji_edit_text)

    }

    private fun setupViewPager() {

        fragmentList = ArrayList()

        mRecentEmojiList = ArrayList(Utils.getRecentEmojis(this))

        if (mRecentEmojiList.size > 0) {
            fragmentList?.add(EmojiFragment().newInstance(mRecentEmojiList, getString(R.string.recent)))
        }

        fragmentList?.add(EmojiFragment().newInstance(SmileyList.getPeopleSmiley()!!, getString(R.string.smiley)))
        fragmentList?.add(EmojiFragment().newInstance(AnimalNature.getNatureSmiley()!!, getString(R.string.animal)))
        fragmentList?.add(EmojiFragment().newInstance(Activities.getActivitiesSmiley()!!, getString(R.string.activity)))
        fragmentList?.add(EmojiFragment().newInstance(FoodDrink.getFoodDrinkSmiley()!!, getString(R.string.food)))
        fragmentList?.add(EmojiFragment().newInstance(Object.getObjectsSmiley()!!, getString(R.string.objects)))
        fragmentList?.add(EmojiFragment().newInstance(Symbols.getSymbolsSmiley()!!, getString(R.string.symbol)))
        fragmentList?.add(EmojiFragment().newInstance(TravelsPlaces.getTravelSmiley()!!, getString(R.string.travel)))
        fragmentList?.add(EmojiFragment().newInstance(Flags.getFlagSmiley()!!, getString(R.string.flags)))

        viewPager = findViewById<ViewPager>(R.id.container)
        emojiAdapter = EmojiPagerAdapter(supportFragmentManager, fragmentList!!)
        viewPager?.adapter = emojiAdapter

        val mTblMain = findViewById<TabLayout>(R.id.tabs_main)
        mTblMain.setupWithViewPager(viewPager)

        if (mRecentEmojiList.size > 0) {

            icons = intArrayOf(R.drawable.ic_emoji_recent_light,
                    R.drawable.ic_emoji_people_light,
                    R.drawable.ic_emoji_nature_light,
                    R.drawable.ic_emoji_people_light,
                    R.drawable.ic_emoji_people_light,
                    R.drawable.ic_emoji_objects_light,
                    R.drawable.ic_emoji_symbols_light,
                    R.drawable.ic_emoji_places_light,
                    R.drawable.ic_emoji_people_light)
        } else {
            icons = intArrayOf(R.drawable.ic_emoji_people_light,
                    R.drawable.ic_emoji_nature_light,
                    R.drawable.ic_emoji_people_light,
                    R.drawable.ic_emoji_people_light,
                    R.drawable.ic_emoji_objects_light,
                    R.drawable.ic_emoji_symbols_light,
                    R.drawable.ic_emoji_places_light,
                    R.drawable.ic_emoji_people_light)
        }

        for (i in 0 until mTblMain.tabCount) {
            mTblMain.getTabAt(i)!!.setIcon(icons!![i])
        }

        viewPager?.addOnPageChangeListener(this)


    }

    private fun initEmojiCompat() {

        val config: EmojiCompat.Config
        if (USEBUNDLEDEMOJI) {
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

        val strText = mTvEmojiText?.text
        mTvEmojiText?.text = "$strText$emoji"
        mEdEmojiEdit?.setText("$strText$emoji")

        //store in recent
        Utils.setRecentEmoji(this, emoji)

        if(Utils.getRecentEmojis(this).size == 1){
            var pos = viewPager?.currentItem
            setupViewPager()
            pos = pos?.plus(1)
            viewPager?.setCurrentItem(pos!!)
        }

    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {
        if(p0 == 0) {
            if (mRecentEmojiList.size > 0) {
                (emojiAdapter?.getCurrentFragment() as EmojiFragment).setNewEmoji(ArrayList(Utils.getRecentEmojis(this)))

            }
        }
    }
}
