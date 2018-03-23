package murzin.andrey.testproject.presentation.category;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.app.Layout;
import murzin.andrey.testproject.data.category.CategoryGetway;
import murzin.andrey.testproject.data.category.CategoryRepository;
import murzin.andrey.testproject.data.model.Category;
import murzin.andrey.testproject.navigation.Screen;
import murzin.andrey.testproject.presentation.base.BaseFragment;
import ru.terrakok.cicerone.Router;

@Layout(R.layout.fragment_category)
public class FragmentCategory extends BaseFragment implements AdapterCategory.CategoryListner {

    private final static String KEY_CATEGORY = "key_category";

    @Inject
    CategoryGetway repository;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    Router router;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    public static FragmentCategory newInstance(String categorySport) {
        Bundle args = new Bundle();
        args.putString(KEY_CATEGORY, categorySport);
        FragmentCategory fragment = new FragmentCategory();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String category = getArgCategory();
        if (category != null) {
            showProgress();
            compositeDisposable
                    .add(repository.getCategory(category)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(categoryModel -> {
                                hideProgress();
                                setDataList(categoryModel);

                            }, throwable -> {
                                hideProgress();
                                showError(throwable.getMessage());
                            }));
        }
    }

    @Override
    protected void initUi() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setDataList(Category categoryModel) {
        AdapterCategory adapterCategory = new AdapterCategory(categoryModel.getEvents(),
                this);
        recyclerView.setAdapter(adapterCategory);
    }

    private String getArgCategory() {
        if (getArguments() != null) return getArguments().getString(KEY_CATEGORY);
        return null;
    }

    @Override
    public void onClickCategory(String article) {
        router.navigateTo(Screen.ARTICLE_SCREEN, article);
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
