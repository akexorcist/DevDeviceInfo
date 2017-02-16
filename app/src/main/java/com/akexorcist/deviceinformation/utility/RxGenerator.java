package com.akexorcist.deviceinformation.utility;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Akexorcist on 2/17/2017 AD.
 */

public class RxGenerator {
    private static RxGenerator rxGenerator;

    public static RxGenerator getInstance() {
        if (rxGenerator == null) {
            rxGenerator = new RxGenerator();
        }
        return rxGenerator;
    }

    public Observable createDelayObservable(long delay, TimeUnit timeUnit) {
        return Observable.empty()
                .delay(delay, timeUnit)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
