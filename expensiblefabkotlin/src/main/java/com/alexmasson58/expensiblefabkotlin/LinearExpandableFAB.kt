package com.alexmasson58.expensiblefabkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Created by frup66058 on 16/05/2018.
 */
class LinearExpandableFAB(context: Context, attributeSet: AttributeSet) : ExpandableFab<LinearLayout>(context, attributeSet) {
    private lateinit var layoutParamOpen: LayoutParams
    private lateinit var layoutParamClose: LayoutParams


    override fun getContentLayout(): LinearLayout {
        return content as LinearLayout
    }

    override fun doNonGenericDraw() {
        if (selectedIndex != -1) {

            //soit close, le choisi est visible en noir
            //soit open, tous visible en noir, et le selected en selected

            when {
                open -> {
                    optionsViews.forEach {
                        it.layoutParams = layoutParamOpen
                    }
                }
                else -> {
                    optionsViews.forEach {
                        it.layoutParams = layoutParamClose
                    }
                }
            }

        }
    }

    override fun loadContent() {

        View.inflate(context, R.layout.expandable_fab, this) // your layout with <merge> as the root tag
        content = findViewById(R.id.content)

    }

    override fun loadOptions() {


        //créer les view
        layoutParamOpen = LinearLayout.LayoutParams(
                context.resources.getDimension(R.dimen.fab_icon_size).toInt(),
                context.resources.getDimension(R.dimen.fab_icon_size).toInt())
        layoutParamOpen.marginStart = context.resources.getDimension(R.dimen.fab_margin).toInt()
        layoutParamOpen.marginEnd = context.resources.getDimension(R.dimen.fab_margin).toInt()

        layoutParamClose = LinearLayout.LayoutParams(
                context.resources.getDimension(R.dimen.fab_icon_size).toInt(),
                context.resources.getDimension(R.dimen.fab_icon_size).toInt())
        layoutParamClose.marginStart = 0
        layoutParamClose.marginEnd = 0

        options.forEach {
            val v = ImageView(context)
            v.setImageDrawable(context.getDrawable(it.iconId))
            v.visibility = View.VISIBLE
            v.layoutParams = layoutParamClose
            optionsViews.add(v)
        }
        //ajoute les dans la customView
        //ajouter des listener
        optionsViews.forEachIndexed { index, view ->
            getContentLayout().addView(view)
            view.setOnClickListener {
                selectedIndex = index
                open = !open
                if (listener != null) {
                    listener!!.onOptionClicked(options[index])
                }
                draw()
            }
        }
    }

}