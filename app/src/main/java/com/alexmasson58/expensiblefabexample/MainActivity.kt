package com.alexmasson58.expensiblefabexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.alexmasson58.expensiblefabkotlin.ExpandableFab
import com.alexmasson58.expensiblefabkotlin.GridExpendableFab
import com.alexmasson58.expensiblefabkotlin.LinearExpandableFAB

class MainActivity : AppCompatActivity(), ExpandableFab.Listener {
    override fun onOptionClicked(o: ExpandableFab.Option) {
        o.returnCode()
    }


    @BindView(R.id.grid)
    lateinit var grid: GridExpendableFab

    @BindView(R.id.linear)
    lateinit var linear: LinearExpandableFAB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        listOf(grid, linear).forEach {
            it.listener = this
            it.setOptions(
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() })
            )
            it.invalidate()
        }

    }
}
