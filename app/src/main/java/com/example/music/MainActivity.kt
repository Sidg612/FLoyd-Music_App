package com.example.music

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.music_player.*


class MainActivity : AppCompatActivity() {


    lateinit var mediaplayer:MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliding_layout.addPanelSlideListener(object :SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View?, slideOffset: Float) {

                bannerPlayBtn.apply {  alpha=1-slideOffset}

                bannerNextBtn.apply { alpha=1-slideOffset}

            }
            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?,
                                             newState: SlidingUpPanelLayout.PanelState?
            )
            {}

        })

        SongListBtn.setOnClickListener {
            startActivity(Intent(this,SongList::class.java))
        }

        val currentsongpsoTHread= object: Thread(){
            override fun run() {

                var totalDuration= mediaplayer.duration
                var currentPosition= 0

                while(currentPosition<totalDuration){

                    try {

                        sleep(500)
                        currentPosition=mediaplayer.currentPosition
                        SongSeekbar.progress=currentPosition

                    }catch (e:InterruptedException){

                        e.printStackTrace()
                    }

                }


            }


        }

        if(mediaplayer!=null){

            mediaplayer.stop()
            mediaplayer.release()
        }









    }


}
