package app.novan.bolamani.com.bolic.playerdetail

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity:AppCompatActivity() {

    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
        player= intent?.extras?.getParcelable("playerDetail")
        showDetailPlayer(player)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showDetailPlayer(playerdetail:Player?){
        supportActionBar?.title=playerdetail?.strPlayer
        Picasso.get().load(playerdetail?.strThumb).into(bannerPlayer)
        playerTextWeight.text=playerdetail?.strWeight
        playerTextHeight.text=playerdetail?.strHeight
        playerPosition.text=playerdetail?.strPosition
        playerOverview.text=playerdetail?.strDescriptionEN
    }

}