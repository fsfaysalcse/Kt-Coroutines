package com.faysal.coroutinesdemo

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.faysal.coroutinesdemo.models.Comments
import com.faysal.coroutinesdemo.models.Post
import com.faysal.coroutinesdemo.network.ApiInterface
import com.faysal.coroutinesdemo.network.NetworkBuilder
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivityKDFDF"
    }

    lateinit var textView: TextView

    lateinit var apiInterface: ApiInterface

    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiInterface = NetworkBuilder.getInstance().create(ApiInterface::class.java)

        textView = findViewById(R.id.textView)


        val dialoge = ProgressDialog(this)
        dialoge.setTitle("Loading.....")
        dialoge.show()

        Log.d(TAG, "onCreate: Start croutines ")



        
        GlobalScope.launch(Dispatchers.IO) {

           val time = measureTime {
                val post_response = async {
                    val response = apiInterface.getPosts()
                    response
                }.await()

                val comments_response = getPosts()



               val photo_response = async {
                   val response = apiInterface.getPhotos()
                   response
               }.await()

               Log.d(TAG, "onCreate : Post : "+post_response.body()?.get(0))
               Log.d(TAG, "onCreate : Comments : "+comments_response.body()?.get(0))
               Log.d(TAG, "onCreate : Photos : "+photo_response.body()?.get(0))

               runBlocking {
                   delay(5000L)
               }


               withContext(Dispatchers.Main){
                    textView.text = post_response.body()?.get(0)?.title.toString()


                    dialoge.dismiss()
                }

            }

            Log.d(TAG, "onCreate: Require time "+time)




        }

        Log.d(TAG, "onCreate: Finished coroutines")

    


    }

    suspend fun getPosts() : Response<Comments> {

        val response = GlobalScope.async(Dispatchers.IO) {
            val response = apiInterface.getComments()
            response
        }.await()

        return response
    }


}