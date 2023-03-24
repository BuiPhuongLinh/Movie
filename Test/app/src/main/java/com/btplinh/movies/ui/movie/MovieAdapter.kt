package com.btplinh.movies.ui.movie

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.btplinh.domain.entities.Movie
import com.btplinh.movies.databinding.ItemLoadingBinding
import com.btplinh.movies.databinding.ItemMovieBinding
import com.btplinh.movies.utils.Constant
import com.bumptech.glide.Glide

class MovieAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movies: ArrayList<Movie?> = arrayListOf()

    fun addNewData(dataViews: ArrayList<Movie>) {
        this.movies = ArrayList()
        this.movies.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun addData(dataViews: ArrayList<Movie>) {
        this.movies.addAll(dataViews)
        notifyDataSetChanged()
    }

    fun addLoadingView() {
        Handler().post {
            movies.add(null)
            notifyItemInserted(movies.size - 1)
        }
    }

    fun removeLoadingView() {
        if (movies.size != 0) {
            movies.removeAt(movies.size - 1)
            notifyItemRemoved(movies.size)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies[position] == null) Constant.VIEW_TYPE_LOADING.value else Constant.VIEW_TYPE_ITEM.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constant.VIEW_TYPE_ITEM.value) {
            val itemMovieBinding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ViewHolder(itemMovieBinding)
        } else {
            val itemLoadingBinding = ItemLoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            LoadingHolder(itemLoadingBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constant.VIEW_TYPE_ITEM.value) {
            (holder as ViewHolder).bind(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size


    class ViewHolder(private val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(movie: Movie?) {
            movie?.let {
                view.apply {
                    Glide.with(view.root.context).load(movie.Poster).into(imgPoster)
                    tvTitle.text = movie.Title
                    tvYear.text = movie.Year
                }
            }

        }

    }

    class LoadingHolder(view: ItemLoadingBinding) : RecyclerView.ViewHolder(view.root)
}

