package com.lcaohoanq.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class Main {

    public static void main(String[] args) {
        Observable<String> obs1 = Observable.just("Data1 asfasdfsa");
        Observable<String> obs2 = Observable.just("Data2");

        Observable.zip(obs1, obs2,
                       (data1, data2) -> data1 + data2)
            .subscribe(System.out::println);
    }
    
}
