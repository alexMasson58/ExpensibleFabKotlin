package com.alexmasson58.expensiblefabexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.alexmasson58.expensiblefabkotlin.ExpandableFab
import com.alexmasson58.expensiblefabkotlin.Option
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ExpandableFab.Listener {
    override fun onOptionClicked(o: ExpandableFab.Option) {
        o.returnCode()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listOf(linear).forEach {
            //it.listener = this
            it.setOptions(
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    ExpandableFab.Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() })
            )
        }
        listOf(flo).forEach {
            //it.listener = this
            it.setOptions(
                    Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() }),
                    Option(R.drawable.ic_baseline_add_24px, { Toast.makeText(this@MainActivity, "add", Toast.LENGTH_SHORT).show() }),
                    Option(R.drawable.ic_baseline_edit_24px, { Toast.makeText(this@MainActivity, "edit", Toast.LENGTH_SHORT).show() })
            )
        }

    }
}
