package com.example.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song_list_item.view.*

class SongListAdapter(val song: ArrayList<Audio>) :
    RecyclerView.Adapter<SongListAdapter.ItemVieHolder>() {
    var onItemClickListener: SongItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.song_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = song.size

    override fun onBindViewHolder(holder: ItemVieHolder, position: Int) {
        holder.bind(song[position])

    }

    inner class ItemVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(audio: Audio) {
            itemView.apply {

                SongName.text= audio.name

                setOnClickListener {
                    onItemClickListener?.onItemClick(audio)
                }
            }

        }
    }
}

interface SongItemClickListener {
    fun onItemClick(audios: Audio)
}
