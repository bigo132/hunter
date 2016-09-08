package com.tom.hunter.framework;

/**
 * Created by txu1 on 9/2/2016.
 */
public interface IBaseView<T extends IBasePresenter> {

    void setPresenter(T presenter);
}
