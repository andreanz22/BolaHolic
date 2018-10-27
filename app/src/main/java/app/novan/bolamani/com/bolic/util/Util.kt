package app.novan.bolamani.com.bolic.util

import android.util.Log
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility=View.GONE
}

fun TextView.formatDate(date:String?){
    date.let {
        val dateBefore= SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date)
        val dateAfter = SimpleDateFormat("EEE, dd MMM yyyy", Locale("in", "ID")).format(dateBefore)

        text = dateAfter
    }
}

fun TextView.formatTime(time:String?){
    if(time!=null){
        time.let {
            val timeBefore= SimpleDateFormat("HH:mm", Locale.US)
            timeBefore.timeZone= TimeZone.getTimeZone("GMT")
            val temp1=timeBefore.parse(time)
            val timeAfter = SimpleDateFormat("HH:mm", Locale.CHINA)

            text = timeAfter.format(temp1)
        }
    }else{
        text = "not defined"
    }
}