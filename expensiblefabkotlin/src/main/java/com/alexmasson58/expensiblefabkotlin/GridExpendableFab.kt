package com.alexmasson58.expensiblefabkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout


/**
 * Created by frup66058 on 16/05/2018.
 */
class GridExpendableFab(context: Context, attributeSet: AttributeSet) : ExpandableFab<GridLayout>(context, attributeSet) {
    override fun doNonGenericDraw() {
        when {
            open -> {
                val padding = context.resources.getDimension(R.dimen.expfab_margin).toInt()
                getContentLayout().setPadding(padding, padding, padding, padding)
            }
            else -> {
                getContentLayout().setPadding(0, 0, 0, 0)
            }
        }
    }

    override fun loadContent() {
        View.inflate(context, R.layout.expandable_fab_grid, this) // your layout with <merge> as the root tag
        content = findViewById(R.id.content)
    }

    override fun loadOptions() {


        val total = options.size
        val column = 4
        val row = total / column
        getContentLayout().setAlignmentMode(GridLayout.ALIGN_BOUNDS)
        getContentLayout().setColumnCount(column)
        getContentLayout().setRowCount(row + 1)
        val params = LinearLayout.LayoutParams(
                context.resources.getDimension(R.dimen.expfab_icon_size).toInt(),
                context.resources.getDimension(R.dimen.expfab_icon_size).toInt())
        options.forEach {
            val v = ImageView(context)
            v.setImageDrawable(context.getDrawable(it.iconId))
            v.visibility = View.VISIBLE
            v.layoutParams = params
            optionsViews.add(v)
        }
        //ajoute les dans la customView
        //ajouter des listener
        optionsViews.forEachIndexed { index, view ->
            getContentLayout().addView(view)
            var param = GridLayout.LayoutParams()
            param.height = context.resources.getDimension(R.dimen.expfab_icon_size).toInt()
            param.width = context.resources.getDimension(R.dimen.expfab_icon_size).toInt()
            param.marginStart = context.resources.getDimension(R.dimen.expfab_margin).toInt()
            param.marginEnd = context.resources.getDimension(R.dimen.expfab_margin).toInt()
            param.topMargin = context.resources.getDimension(R.dimen.expfab_margin).toInt()
            param.bottomMargin = context.resources.getDimension(R.dimen.expfab_margin).toInt()
            param.leftMargin = context.resources.getDimension(R.dimen.expfab_margin).toInt()
            param.rightMargin = context.resources.getDimension(R.dimen.expfab_margin).toInt()
            param.setGravity(Gravity.CENTER)
            param.columnSpec = GridLayout.spec((index / column) + index % column)
            param.rowSpec = GridLayout.spec(index / column)
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

    override fun getContentLayout(): GridLayout {
        return content!!
    }
}