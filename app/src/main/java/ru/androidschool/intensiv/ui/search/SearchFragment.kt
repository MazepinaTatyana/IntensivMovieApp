package ru.androidschool.intensiv.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_toolbar.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.ui.afterTextChanged
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString(KEY_SEARCH)
        search_toolbar.setText(searchTerm)

        val subject: PublishSubject<String> = PublishSubject.create()
        subject.onNext({
            search_edit_text.afterTextChanged { text ->
                text?.filter { text.length > LENGTH_TEXT }
                    ?.map { it.toString().trim() }
                if (!text.isNullOrEmpty() && !delete_text_button.isVisible) {
                    delete_text_button.visibility = View.VISIBLE
                }
                if (text.isNullOrEmpty() && delete_text_button.isVisible) {
                    delete_text_button.visibility = View.GONE
                }
            }
        }.toString())
        subject.debounce(TIMEOUT_DEBOUNCE, TimeUnit.MILLISECONDS)
            .doOnSubscribe { progress_search.visibility = View.VISIBLE }
            .doFinally { progress_search.visibility = View.INVISIBLE }
            .subscribe()
    }

    companion object {
        private const val LENGTH_TEXT = 3
        private const val TIMEOUT_DEBOUNCE = 500L
    }
}
