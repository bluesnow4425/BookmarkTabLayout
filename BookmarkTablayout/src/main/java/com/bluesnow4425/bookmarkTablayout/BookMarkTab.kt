package com.bluesnow4425.bookmarkTablayout

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bluesnow4425.bookmarkTablayout.databinding.ComponentBookmarkTabBinding

/**
 * Custom bookmark view class that can easily be used in xml.
 */
class BookMarkTab @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    var binding: ComponentBookmarkTabBinding

    var hintTextColor: ColorStateList
        get() = binding.bookmarkHint.textColors
        set(value) {
            binding.bookmarkHint.setTextColor(value)
        }

    var titleTextColor: ColorStateList
        get() = binding.bookmarkTitle.textColors
        set(value) {
            binding.bookmarkTitle.setTextColor(value)
        }

    var titleTextSize
        get() = binding.bookmarkTitle.textSize.toInt()
        set(value) {
            binding.bookmarkTitle.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                value.toFloat()
            )
        }

    var hintTextSize: Int
        get() = binding.bookmarkHint.textSize.toInt()
        set(value) {
            binding.bookmarkHint.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                value.toFloat()
            )
        }

    var tabStyle: TabStyle
        get() = if (background.constantState == getDrawable(
                context,
                R.drawable.ic_tab_sharp
            )
        ) TabStyle.Sharp else TabStyle.Rounded
        set(value) {
            setBackgroundResource(if (value == TabStyle.Sharp) R.drawable.ic_tab_sharp else R.drawable.tab_dark_no_padding)
        }


    init {
        binding =
            ComponentBookmarkTabBinding.inflate(LayoutInflater.from(context), this@BookMarkTab)

        var typefaceType =
            context.obtainStyledAttributes(attrs, R.styleable.BookMarkTab, 0, 0)

        try {

            //set icon
            if (typefaceType.hasValue(R.styleable.BookMarkTab_icon)) {
                binding.bookmarkIcon.setImageDrawable(typefaceType.getDrawable(R.styleable.BookMarkTab_icon))
            } else {
                binding.bookmarkIcon.visibility = View.GONE
            }

            //set hint
            if (typefaceType.hasValue(R.styleable.BookMarkTab_hint)) {
                var hint = typefaceType.getString(R.styleable.BookMarkTab_hint)
                binding.bookmarkHint.setText(hint)
            } else {
                binding.bookmarkHint.visibility = View.GONE
            }


            //set title
            var title = typefaceType.getString(R.styleable.BookMarkTab_title)
            binding.bookmarkTitle.setText(title)

            //set background color
            if (typefaceType.hasValue(R.styleable.BookMarkTab_backgroundColor)) {
                var backgroundColor =
                    typefaceType.getColorStateList(R.styleable.BookMarkTab_backgroundColor)
                backgroundTintList = backgroundColor
            } else {
                backgroundTintList =
                    AppCompatResources.getColorStateList(context, R.color.tab_bg_color)
            }

            //set text sizes
            if (typefaceType.hasValue(R.styleable.BookMarkTab_titleTextSize)) {
                titleTextSize =
                    typefaceType.getDimensionPixelSize(R.styleable.BookMarkTab_titleTextSize, 0)
            }

            if (typefaceType.hasValue(R.styleable.BookMarkTab_hintTextSize)) {
                hintTextSize =
                    typefaceType.getDimensionPixelSize(R.styleable.BookMarkTab_hintTextSize, 0)
            }

            //set text colors
            if (typefaceType.hasValue(R.styleable.BookMarkTab_titleTextColor)) {
                titleTextColor =
                    typefaceType.getColorStateList(R.styleable.BookMarkTab_titleTextColor)!!
            }
            if (typefaceType.hasValue(R.styleable.BookMarkTab_hintTextColor)) {
                hintTextColor =
                    typefaceType.getColorStateList(R.styleable.BookMarkTab_hintTextColor)!!
            }

            //set style
            var bg = typefaceType.getEnum(R.styleable.BookMarkTab_style, TabStyle.Rounded)
            tabStyle = bg

            //set selected
            var defaultSelected =
                typefaceType.getBoolean(R.styleable.BookMarkTab_defaultSelected, false)
            elevation = if (defaultSelected) 2F else 1F
            isSelected = defaultSelected
        } finally {
            typefaceType.recycle()
        }


        val scale = context.resources.displayMetrics.density
        var endPadding = 12F
        if (hintTextSize != 0 || titleTextSize != 0) {
            var size = titleTextSize
            if (hintTextSize > titleTextSize) size = hintTextSize
            endPadding = size * 0.24F
        }

        setPadding(
            (1F * scale).toInt(),
            (4F * scale).toInt(),
            ((endPadding * scale) + resources.getDimensionPixelSize(R.dimen.tab_start_space)).toInt(),
            (4F * scale).toInt()
        )
    }


    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        binding.bookmarkTitle.isSelected = selected
        binding.bookmarkHint.isSelected = selected
        binding.bookmarkIcon.isSelected = selected

        elevation = if (isSelected) 2F else 1F

        if (selected) {
            selectedListener?.invoke(this)
        }
    }

    enum class TabStyle {
        Rounded, Sharp
    }

    private var selectedListener: ((View) -> Unit)? = null
    fun setOnSelectedListener(func: (v: View) -> Unit) {
        selectedListener = func
    }

    companion object Binding {
        @BindingAdapter("app:onSelectedInvoke")
        @JvmStatic
        fun selected(tab: BookMarkTab, func: (v: View) -> Unit) {
            tab.setOnSelectedListener(func)
        }
    }
}

inline fun <reified T : Enum<T>> TypedArray.getEnum(index: Int, default: T) =
    getInt(index, -1).let {
        if (it >= 0) enumValues<T>()[it] else default
    }

