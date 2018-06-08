package com.alexmasson58.expensiblefabexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.alexmasson58.expensiblefabkotlin.*

class MainActivity : AppCompatActivity(), ExpensibleFab.Listener {
    override fun onOptionClicked(o: ExpensibleFab.Option) {
        o.returnCode()
    }

    @BindView(R.id.linear)
    lateinit var linear: LinearExpensibleFAB


    @BindView(R.id.closedIcon)
    lateinit var closedIcon: LinearExpensibleFAB


    @BindView(R.id.notautoclose)
    lateinit var notautoclose: LinearExpensibleFAB

    @BindView(R.id.grid)
    lateinit var grid: GridExpensibleFab

    @BindView(R.id.flo)
    lateinit var flo: ExpensibleFabCanvas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        listOf(linear, closedIcon, notautoclose).forEach {
            it.setOptions(ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }))
        }
        listOf(grid).forEach {
            //it.listener = this
            it.setOptions(
                    ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpensibleFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() })
            )
        }
        listOf(flo).forEach {
            it.setOptions(
                    Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() })
            )
        }


    }
}
