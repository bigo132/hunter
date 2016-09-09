package com.tom.hunter.framework;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by txu1 on 9/6/2016.
 */
public class RxBus {

    private static volatile RxBus instance;

    private final Subject<Object, Object> bus;

    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

    public Observable toObserverable(Class eventType) {
        return bus.ofType(eventType);
    }

}
