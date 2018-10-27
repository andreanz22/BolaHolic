package app.novan.bolamani.com.bolic.api

import java.net.URL

class ApiRepository{
    fun doRequest(url: String):String{
        return URL(url).readText()
    }
}