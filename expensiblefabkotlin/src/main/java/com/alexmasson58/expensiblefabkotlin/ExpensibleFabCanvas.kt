package com.alexmasson58.expensiblefabkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.github.florent37.mylittlecanvas.animation.ShapeAnimator
import com.github.florent37.mylittlecanvas.shape.RectShape


/**
 * created by florent37, implementation with his lib littleCanvas
 */
public class ExpensibleFabCanvas(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    internal val options = mutableListOf<Option>()
    internal val optionWidth: Int by lazy {
        context.resources.getDimensionPixelOffset(R.dimen.expfab_size)
    }

    //region shape
    val shapeAnimator = ShapeAnimator(this)
    val backgroundShape: RectShape = RectShape().apply {
        color = Color.WHITE
    }
    //endregion

    private var opened = false

    internal val content = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
        this@ExpensibleFabCanvas.addView(this)
    }
    internal val optionsViews = mutableListOf<View>()

    init {
        setWillNotDraw(false)
    }

    private fun onOptionClicked(view: View, option: Option) {
        optionsViews.forEach {
            it.isSelected = false
        }
        view.isSelected = true

        if (opened) {
            collapse()
            //avert listener
        } else {
            expand()
        }

    }

    private fun collapse() {
        val left = (width - optionWidth).toFloat()

        optionsViews.forEachIndexed { index, view ->
            view.animate().cancel()
            view.animate().x(left)

            if(!view.isSelected){
                view.animate().alpha(0f)
            }
        }

        shapeAnimator.clear()
                .play(backgroundShape.animate().leftTo(left))
                .onAnimationEnd {
                    opened = false
                }
                .start()
    }


    private fun expand() {
        val minLeft = (width - optionWidth * options.size).toFloat()

        optionsViews.forEachIndexed { index, view ->
            view.animate().cancel()
            view.animate()
                    .alpha(1f)
                    .x(minLeft + (optionWidth * index).toFloat())
        }

        val left = (width - optionWidth * options.size).toFloat()

        shapeAnimator.clear()
                .play(backgroundShape.animate().leftTo(left))
                .onAnimationEnd {
                    opened = true
                }
                .start()
    }

    fun setOptions(vararg options: Option) {
        content.removeAllViews()
        this.options.clear()
        this.options.addAll(options)
        this.options.forEach { option ->
            content.addView(LayoutInflater.from(context).inflate(R.layout.expensible_item, content, false).apply {
                optionsViews.add(this)
                findViewById<ImageView>(R.id.image).apply {
                    setImageResource(option.iconId)
                }
                setOnClickListener {
                    onOptionClicked(it, option)
                }
            })
        }
        content.post {
            val left = (width - optionWidth).toFloat()
            optionsViews.forEachIndexed { index, view ->
                view.x = left
                if(index == 0){
                    view.alpha = 1f
                    view.isSelected = true
                } else {
                    view.alpha = 0f
                    view.isSelected = true
                }

            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (backgroundShape.width == 0f) {
            backgroundShape.cornerRadius = w.toFloat() / 2f
            backgroundShape.right = w.toFloat()
            backgroundShape.left = (w - optionWidth).toFloat()
            backgroundShape.bottom = h.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        backgroundShape.onDraw(canvas)
        super.onDraw(canvas)
    }

}