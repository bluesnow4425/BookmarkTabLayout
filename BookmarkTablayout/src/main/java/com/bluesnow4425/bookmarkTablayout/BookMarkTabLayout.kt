package com.bluesnow4425.bookmarkTablayout

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter

/**
 * Use BookMarkTabLayout like Tablayout.
 * @see BookMarkTab
 */
class BookMarkTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : HorizontalScrollView(context, attrs) {

    private var mTabBackgroundColor: ColorStateList?
    private var mTabTitleSize : Int = 0
    private var mTabHintSize :Int = 0
    private var mTabTitleColor :ColorStateList?
    private var mTabHintColor : ColorStateList?
    private var mItemSelectedListener: ((position: Int) -> Unit)? = null
    private var mTabStyle : BookMarkTab.TabStyle
    private var mAutoScroll = false

    private var mContainer: LinearLayoutCompat

    init {
        inflate(context, R.layout.book_mark_layout_parent, this@BookMarkTabLayout)
        mContainer = findViewById(R.id.ll_tablayout_container)
        isHorizontalScrollBarEnabled = false

        var typefaceType =
            context.obtainStyledAttributes(attrs, R.styleable.BookMarkTabLayout, 0, 0)
        try {
            mTabBackgroundColor = typefaceType.getColorStateList(R.styleable.BookMarkTabLayout_tabBackgroundColor)

            mTabHintColor = typefaceType.getColorStateList(R.styleable.BookMarkTabLayout_tabHintTextColor)
            mTabTitleColor = typefaceType.getColorStateList(R.styleable.BookMarkTabLayout_tabTitleTextColor)

            mTabHintSize =
                typefaceType.getDimensionPixelSize(R.styleable.BookMarkTabLayout_tabHintTextSize, 0)
            mTabTitleSize =
                typefaceType.getDimensionPixelSize(R.styleable.BookMarkTabLayout_tabTitleTextSize, 0)

            mTabStyle = typefaceType.getEnum(R.styleable.BookMarkTabLayout_style,
                BookMarkTab.TabStyle.Rounded
            )
            mAutoScroll = typefaceType.getBoolean(R.styleable.BookMarkTabLayout_autoScrollOnSelected, false)

        } finally {
            typefaceType.recycle()
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child?.id == R.id.ll_tablayout_container) {
            super.addView(child, params)
        } else {
            if (child is BookMarkTab) {
                if (mTabBackgroundColor!=null) child.backgroundTintList = mTabBackgroundColor
                child.tabStyle = mTabStyle
                if (mTabHintColor!=null) child.hintTextColor = mTabHintColor!!
                if (mTabTitleColor!=null) child.titleTextColor = mTabTitleColor!!
                if (mTabTitleSize!=0) child.titleTextSize = mTabTitleSize!!.toInt()
                if (mTabHintSize!=0) child.hintTextSize = mTabHintSize!!.toInt()
            }
            mContainer.addView(child)
        }
    }


    override fun onFinishInflate() {
        super.onFinishInflate()

        for (position in 0 until mContainer.childCount) {
            var childView = mContainer.getChildAt(position) as BookMarkTab

            childView.setOnClickListener {
                if (childView.isSelected) return@setOnClickListener
                for (other in 0 until mContainer.childCount) {
                    mContainer.getChildAt(other).isSelected = false
                }
                childView.isSelected = true
                if (mAutoScroll) smoothScrollTo(childView.x.toInt(), 0)
                if (mItemSelectedListener != null) mItemSelectedListener!!.invoke(position)
            }

            if (position == 0) {
                childView.isSelected = true
                continue
            } else {
                childView.isSelected = false
            }

            var params = childView!!.layoutParams as LinearLayout.LayoutParams
            params.setMargins(
                ((resources.getDimensionPixelSize(R.dimen.tab_start_space)*-0.6).toInt()),
                0,
                0,
                0
            )
        }
    }

    fun setOnItemSelectedListener(func: ((position: Int) -> Unit)) {
        mItemSelectedListener = func
    }


    companion object Binding {
        @BindingAdapter("app:onItemSelectedInvoke")
        @JvmStatic
        fun selected(tab: BookMarkTabLayout, func: (position: Int) -> Unit) {
            tab.setOnItemSelectedListener(func)
        }
    }
}

