package com.thgcode.kotlinrv.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.thgcode.kotlinrv.R
import com.thgcode.kotlinrv.adapter.ProgrammingLinguageAdapter
import com.thgcode.kotlinrv.adapter.RepositoryAdapter
import com.thgcode.kotlinrv.api.GithubRepositoriesResult
import com.thgcode.kotlinrv.api.RepositoryRetriver
import com.thgcode.kotlinrv.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val repositoryRetriver = RepositoryRetriver()

    private val callback = object : Callback<GithubRepositoriesResult> {

        override fun onFailure(call: Call<GithubRepositoriesResult>, t: Throwable) {
            longToast("Load failure.")
        }

        override fun onResponse(call: Call<GithubRepositoriesResult>, response: Response<GithubRepositoriesResult>) {
            longToast("Load finish.")
            response.body()?.repositories?.let { repositories ->
                recyclerView.adapter = RepositoryAdapter(
                    repositories,
                    this@MainActivity
                ) {
                    longToast("Clicked item: ${it.full_name}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDefaultRecyclerView()
    }

    private fun loadDefaultRecyclerView() {
        recyclerView.adapter = ProgrammingLinguageAdapter(
            recyclerViewItems(),
            this
        ) { programmingLanguage ->
            longToast("Clicked item: ${programmingLanguage.title}")

            repositoryRetriver.getLanguageRepositories(
                callback,
                programmingLanguage.title

            )
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun recyclerViewItems(): List<ProgrammingLanguage> {
        val kotlin = ProgrammingLanguage(
            R.drawable.kotlin,
            "Kotlin",
            2010,
            "The best language"
        )

        return listOf(kotlin, kotlin)
    }
}
