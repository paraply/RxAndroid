package se.evinja.rxandroid.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import se.evinja.rxandroid.R

class MainActivity : AppCompatActivity() {

    enum class Example(val label : String) {
        BASIC("Basic example")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
    }

    private fun setupList() {
        val listItems = Example.values().map{it.label}
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        activitiesListView.adapter = adapter
        activitiesListView.setOnItemClickListener { parent, view, position, id ->
            when(position) {
                Example.BASIC.ordinal -> startActivity(Intent(this, BasicExampleActivity::class.java))
            }
        }

    }


}
