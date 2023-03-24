package com.btplinh.movies.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.btplinh.domain.entities.Movie
import com.btplinh.movies.common.DialogLoading
import com.btplinh.movies.databinding.FragmentMovieBinding
import com.btplinh.movies.utils.Constant
import com.btplinh.movies.utils.OnLoadMoreListener
import com.btplinh.movies.utils.RecyclerViewLoadMoreScroll
import com.btplinh.movies.utils.setOnClickListenerWithDebounce
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private var page = 1
    private lateinit var dialogLoading: DialogLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getListMovie(page)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        intAction()
        initObserve()
    }

    private fun intAction() {
        binding.imgSearch.setOnClickListenerWithDebounce {
            page = 1
            binding.tvErrorMessage.visibility = View.GONE
            viewModel.getListMovie(page, binding.edtSearch.text.toString())
        }
    }

    private fun initObserve() {
        viewModel.listMovieLiveData.observe(viewLifecycleOwner) {
            if (page == 1) {
                movieAdapter.addNewData(it as ArrayList<Movie>)
            } else {
                finishLoadMore(it)
            }
        }
        viewModel.showLoadingLiveData.observe(viewLifecycleOwner) {
            if (it && page == 1) {
                dialogLoading.showLoading()
            } else {
                dialogLoading.dismissLoading()
            }
        }
        viewModel.dataErrorLiveData.observe(viewLifecycleOwner) {
            binding.tvErrorMessage.visibility = View.VISIBLE
            binding.tvErrorMessage.text = it
        }
    }

    private fun initView() {
        dialogLoading = DialogLoading(requireContext())
        val mLayoutManager = setupRecyclerView()
        binding.apply {
            rcvMovies.apply {
                layoutManager = mLayoutManager
                adapter = movieAdapter
                setHasFixedSize(true)
                addOnScrollListener(scrollListener)
            }


        }
    }

    private fun setupRecyclerView(): GridLayoutManager {
        movieAdapter = MovieAdapter()
        val mLayoutManager = GridLayoutManager(requireContext(), 2)
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager)
        scrollListener.setOnLoadMoreListener(object :
            OnLoadMoreListener {
            override fun onLoadMore() {
                startLoadMore()
            }
        })
        mLayoutManager.spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (movieAdapter.getItemViewType(position)) {
                        Constant.VIEW_TYPE_ITEM.value -> 1
                        Constant.VIEW_TYPE_LOADING.value -> 2
                        else -> -1
                    }
                }
            }
        return mLayoutManager
    }

    private fun startLoadMore() {
        movieAdapter.addLoadingView()
        page++
        viewModel.getListMovie(page)
    }

    private fun finishLoadMore(movies: List<Movie>) {
        movieAdapter.removeLoadingView()
        movieAdapter.addData(movies as ArrayList<Movie>)
        scrollListener.setLoaded()
        binding.rcvMovies.post {
            movieAdapter.notifyDataSetChanged()
        }
    }
}
