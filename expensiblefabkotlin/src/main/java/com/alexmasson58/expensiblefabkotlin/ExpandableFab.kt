package com.alexmasson58.expensiblefabkotlin

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import java.util.*

/**
 * Created by frup66058 on 14/05/2018.
 * Expandable FAB
 */
abstract class ExpandableFab<T : ViewGroup>(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {


    interface Listener {
        fun onOptionClicked(o: Option)
    }

    class Option(var iconId: Int = -1, var returnCode: () -> Unit)


    internal val options: ArrayList<Option> = ArrayList()
    var listener: Listener? = null
    internal var open = false
    private var autoclose = true
    private val defaultSelectedColor: String = "#018EB0"
    private var selectedTint: Int = Color.parseColor(defaultSelectedColor)
    private val defaultNormalColor: String = "#000000"
    private var normalTint: Int = Color.parseColor(defaultNormalColor)
    internal val optionsViews = ArrayList<ImageView>()
    private var closedView: ImageView? = null

    internal var selectedIndex = -1
    var content: T? = null

    private var closedIcon: Int = -1

    init {
        val styledAttrs = context.obtainStyledAttributes(attributeSet, R.styleable.ExpensibleFab)
        autoclose = styledAttrs.getBoolean(R.styleable.ExpensibleFab_exp_fab_autoclose, true)
        closedIcon = styledAttrs.getResourceId(R.styleable.ExpensibleFab_exp_fab_closed_icon, -1)
        if (closedIcon != -1) {
            closedView = ImageView(context)
            closedView!!.setImageDrawable(context.getDrawable(closedIcon))
            closedView!!.visibility = View.VISIBLE
            closedView!!.setOnClickListener {
                switchOpen()
            }
        }
        styledAttrs.recycle()

    }


    internal fun draw() {
        if (selectedIndex != -1) {
            when {
                open -> {
                    val padding = context.resources.getDimension(R.dimen.expfab_margin).toInt()
                    getContentLayout().setPadding(padding, padding, padding, padding)
                    optionsViews.forEachIndexed { index, imageView ->
                        imageView.visibility = View.VISIBLE
                        imageView.setColorFilter(when (index) {
                            selectedIndex -> selectedTint
                            else -> normalTint
                        })
                    }
                    if (closedIcon != -1) {
                        closedView!!.visibility = View.GONE
                    }
                }
                else -> {
                    getContentLayout().setPadding(0, 0, 0, 0)
                    optionsViews.forEachIndexed { index, imageView ->
                        imageView.visibility = when {
                            (closedIcon == -1) && index == selectedIndex -> View.VISIBLE
                            else -> {
                                GONE
                            }
                        }
                        imageView.setColorFilter(when (index) {
                            selectedIndex -> selectedTint
                            else -> normalTint
                        })
                    }
                    if (closedIcon != -1) {
                        closedView!!.visibility = View.VISIBLE
                    }
                }
            }
        } else {
            optionsViews.forEach {
                it.visibility = GONE
                it.setColorFilter(normalTint, android.graphics.PorterDuff.Mode.SRC_IN)
            }
            optionsViews[0].visibility = View.VISIBLE
        }
        doNonGenericDraw()
    }

    abstract fun doNonGenericDraw()

    private fun collapse() {
        open = false
        draw()
    }

    private fun expand() {
        open = true

        draw()
    }

    internal fun switchOpen() {
        when (open) {
            true -> {
                when (autoclose) {
                    true -> collapse()
                    false -> {
                    } //do nothing stay open
                }
            }
            false -> expand()
        }
    }

    fun setOptions(vararg options: Option) {
        if (options.isEmpty()) {
            throw IllegalArgumentException("options should contains at least one option")
        }
        if (content == null) {
            loadContent()
        }

        selectedIndex = 0
        open = false
        removeOptions()
        this.options.clear()

        this.options.addAll(options)
        loadOptions()
        if (closedIcon != -1) {
            getContentLayout().addView(closedView)
        }

        draw()
    }

    abstract fun loadContent()

    abstract fun loadOptions()

    private fun removeOptions() {
        optionsViews.forEach {
            it.setOnClickListener(null)
        }
        getContentLayout().removeAllViews()
        optionsViews.clear()
    }

    abstract fun getContentLayout(): T
}