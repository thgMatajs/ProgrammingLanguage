package com.thgcode.kotlinrv.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.thgcode.kotlinrv.R
import com.thgcode.kotlinrv.adapter.ProgrammingLinguageAdapter
import com.thgcode.kotlinrv.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter =  ProgrammingLinguageAdapter(
                recyclerViewItems(),
                this) { programmingLanguage ->
            longToast("Clicked item: ${programmingLanguage.title}")
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun recyclerViewItems() : List<ProgrammingLanguage> {
        val kotlin = ProgrammingLanguage(R.drawable.kotlin,
                "Kotlin",
                2010,
                "The best language")

        return listOf(kotlin, kotlin)
    }
}
