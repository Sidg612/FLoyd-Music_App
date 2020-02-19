package com.example.music

import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_song_list.*
import java.text.FieldPosition
import java.time.Duration
import java.util.concurrent.TimeUnit

class SongList : AppCompatActivity() {

    val audiolist= ArrayList<Audio>()


    private val onItemClicked = object :SongItemClickListener{
        override fun onItemClick(audios: Audio, position:Int) {

           // Toast.makeText(this@SongList,"${audios}+ /n/n +${position} ",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this@SongList,MainActivity::class.java)
                .putExtra("SONG",audiolist)
                .putExtra("POSITION", position)
            )
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        Query()

        val songAdapter = SongListAdapter(audiolist)
        songAdapter.onItemClickListener = onItemClicked

            songList.apply {
            layoutManager = LinearLayoutManager(this@SongList,RecyclerView.VERTICAL,false)
            adapter= songAdapter
        }
    }


    fun Query(){

        val projection= arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.SIZE
        )

        //show only the songs which have duration more than 1 minute

        val selection = "${MediaStore.Video.Media.DURATION} >= ?"
        val selectionArgs = arrayOf(
            TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES).toString()
        )
        //sorting order
        val sortOrder = "${MediaStore.Video.Media.DISPLAY_NAME} ASC"

        //query for the internal memory

        val query= contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        query?.use{ cursor ->

            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)

            while (cursor.moveToNext()) {
                // Get values of columns for a given video.
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val duration = cursor.getInt(durationColumn)
                val artistname= cursor.getString(artistColumn)
                val albumName= cursor.getString(albumColumn)
                val size = cursor.getInt(sizeColumn)

                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                // Stores column values and the contentUri in a local object
                // that represents the media file.
                audiolist += Audio(contentUri, name, duration,artistname,albumName,size)
            }
        }

    }
}

data class Audio(

    val uri: Uri,
    val name: String,
    val duration: Int,
    val artistName:String,
    val albumName:String,
    val size:Int


)
