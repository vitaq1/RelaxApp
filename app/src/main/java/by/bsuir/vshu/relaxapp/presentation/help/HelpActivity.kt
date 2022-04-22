package by.bsuir.vshu.relaxapp.presentation.help

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import by.bsuir.vshu.relaxapp.R

class HelpActivity : AppCompatActivity() {
    var adapter: HelpViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)
        viewPager = findViewById(R.id.viewpager)
        adapter = HelpViewPagerAdapter(this)
        viewPager!!.setAdapter(adapter)
    }



    companion object {
        @JvmField
        var viewPager: ViewPager? = null
    }
}