package murzin.andrey.testproject.presentation.article;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.app.Layout;
import murzin.andrey.testproject.data.article.ArticleGetway;
import murzin.andrey.testproject.data.model.ArticleItem;
import murzin.andrey.testproject.presentation.base.BaseFragment;

@Layout(R.layout.fragment_article)
public class FragmentArticle extends BaseFragment {

    private final static String ARTICLE_KEY = "article_key";

    @Inject
    ArticleGetway repository;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public static FragmentArticle newInstance(String article) {
        Bundle args = new Bundle();
        args.putString(ARTICLE_KEY, article);
        FragmentArticle fragment = new FragmentArticle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String article = getArticleArg();
        if (article != null) {
            showProgress();
            compositeDisposable
                    .add(repository.getArticle(article)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(articleModel -> {
                                hideProgress();
                                setDataToList(articleModel.getArticle());
                            }, throwable -> {
                                hideProgress();
                                showError(throwable.getMessage());
                            }));
        }
    }

    private void setDataToList(List<ArticleItem> article) {
        recyclerView.setAdapter(new AdapterArticle(article));
    }

    private String getArticleArg() {
        if (getArguments() != null) return getArguments().getString(ARTICLE_KEY);
        return null;
    }

    @Override
    protected void initUi() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


}
