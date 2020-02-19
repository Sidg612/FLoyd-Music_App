package com.example.music

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_layout.*
import kotlinx.android.synthetic.main.music_player.*


class MainActivity : AppCompatActivity() {


//    lateinit var mediaplayer:MediaPlayer
//
//    lateinit var arraylist: ArrayList<Audio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sliding_layout.addPanelSlideListener(object :SlidingUpPanelLayout.PanelSlideListener{
            override fun onPanelSlide(panel: View?, slideOffset: Float) {

                bannerPlayBtn.apply {  alpha=1-slideOffset}

                bannerNextBtn.apply { alpha=1-slideOffset}

            }
            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?,
                                             newState: SlidingUpPanelLayout.PanelState?)
            {}
        })

        SongListBtn.setOnClickListener {
            startActivity(Intent(this,SongList::class.java))
        }

//        val currentsongpsoThread= object: Thread(){
//            override fun run() {
//
//                val totalDuration= mediaplayer.duration
//                var currentPosition= 0
//
//                while(currentPosition<totalDuration){
//
//                    try {
//                        sleep(500)
//                        currentPosition=mediaplayer.currentPosition
//                        SongSeekbar.progress=currentPosition
//                    }catch (e:InterruptedException){
//                        e.printStackTrace()
//                    }
//               }
//            }
//        }
//
//        currentsongpsoThread.start()
//
//        if(mediaplayer!=null) {
//            mediaplayer.stop()
//            mediaplayer.release()
//        }
//
//        val intent= Intent()
//
//        arraylist = intent.getParcelableArrayExtra("SONG") as ArrayList<Audio>
//        val pos=intent.getIntExtra("POSITION",-1)
//
//        val sname= arraylist[pos].name
//
//        songNameLabel.text=sname
//        songNameLabel.isSelected=true
//
//
//        val uri = arraylist[pos].uri
//
//        mediaplayer= MediaPlayer.create(this, uri)
//
//        mediaplayer.start()
//
//        SongSeekbar.max=mediaplayer.duration
//
//
//        SongSeekbar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//
//                mediaplayer.seekTo(SongSeekbar.progress)
//            }
//
//
//        })
//
//






    }


}
