package murzin.andrey.testproject.presentation.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.disposables.CompositeDisposable;
import murzin.andrey.testproject.R;
import murzin.andrey.testproject.app.Layout;
import murzin.andrey.testproject.delegat.ToolbarTitleDelegate;


public abstract class BaseFragment extends Fragment
        implements HasSupportFragmentInjector {

    @Inject
    protected ToolbarTitleDelegate toolbarTitleDelegate;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    private Unbinder unbinder;
    protected CompositeDisposable compositeDisposable;



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) unbinder.unbind();
        if (compositeDisposable != null) compositeDisposable.isDisposed();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Class<?> cls = this.getClass();
        if (!cls.isAnnotationPresent(Layout.class)) {
            throw new IllegalArgumentException("Please specify LayoutRes for fragment in @Layout annotation");
        }
        Layout layout = cls.getAnnotation(Layout.class);
        View view = inflater.inflate(layout.value(), container, false);
        unbinder = ButterKnife.bind(this, view);
        compositeDisposable = new CompositeDisposable();
        initUi();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    protected abstract void initUi();

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }


    protected void changeTitle(String title) {
        toolbarTitleDelegate.changeTitle(title.toUpperCase());
    }

    protected void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }



}
