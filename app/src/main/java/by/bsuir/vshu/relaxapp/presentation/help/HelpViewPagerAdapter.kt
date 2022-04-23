package by.bsuir.vshu.relaxapp.presentation.help

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.help.HelpActivity

class HelpViewPagerAdapter(var ctx: Context) : PagerAdapter() {
    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slide_screen, container, false)
        val logo = view.findViewById<ImageView>(R.id.logo)
        val ind1 = view.findViewById<ImageView>(R.id.ind1)
        val ind2 = view.findViewById<ImageView>(R.id.ind2)
        val ind3 = view.findViewById<ImageView>(R.id.ind3)
        val desc = view.findViewById<TextView>(R.id.desc)
        val next = view.findViewById<ImageView>(R.id.next)
        val back = view.findViewById<ImageView>(R.id.back)

        next.setOnClickListener { HelpActivity.viewPager!!.currentItem = position + 1 }
        back.setOnClickListener { HelpActivity.viewPager!!.currentItem = position - 1 }
        when (position) {
            0 -> {
                logo.setImageResource(R.drawable.logo1)
                ind1.setImageResource(R.drawable.seleted)
                ind2.setImageResource(R.drawable.unselected)
                ind3.setImageResource(R.drawable.unselected)
                desc.text = "Смотрите гороскоп, выбирайте настроение"
                back.visibility = View.GONE
                next.visibility = View.VISIBLE
            }
            1 -> {
                logo.setImageResource(R.drawable.logo2)
                ind1.setImageResource(R.drawable.unselected)
                ind2.setImageResource(R.drawable.seleted)
                ind3.setImageResource(R.drawable.unselected)
                desc.text = "Слушайте расслабляющую музыку"
                back.visibility = View.VISIBLE
                next.visibility = View.VISIBLE
            }
            2 -> {
                logo.setImageResource(R.drawable.logo3)
                ind1.setImageResource(R.drawable.unselected)
                ind2.setImageResource(R.drawable.unselected)
                ind3.setImageResource(R.drawable.seleted)
                desc.text = "Настраивайте профиль пользователя"
                back.visibility = View.VISIBLE
                next.visibility = View.GONE
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}