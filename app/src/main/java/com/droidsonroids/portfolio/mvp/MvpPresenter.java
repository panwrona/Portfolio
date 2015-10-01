package com.droidsonroids.portfolio.mvp;

public interface MvpPresenter<V extends MvpView>  {

    void attachView(V view);
    void detachView();
}
