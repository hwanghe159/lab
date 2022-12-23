package com.example.webflux;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import org.junit.jupiter.api.Test;

public class MonoTest {

  @Test
  void name() {
    Iterable<Integer> itr = Arrays.asList(1, 2, 3, 4, 5);

    Publisher<Integer> p = new Publisher<Integer>() {
      @Override
      public void subscribe(Subscriber subscriber) {
        Iterator<Integer> it = itr.iterator();
        subscriber.onSubscribe(new Subscription() {
          @Override
          public void request(long n) {
            while (n-- > 0) {
              if (it.hasNext()) {
                subscriber.onNext(it.next());
              } else {
                subscriber.onComplete();
                break;
              }
            }
          }

          @Override
          public void cancel() {

          }
        });
      }
    };

    Subscriber<Integer> s = new Subscriber<Integer>() {
      Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscribe");
        this.subscription = subscription;
        this.subscription.request(1);
      }

      @Override
      public void onNext(Integer item) {
        System.out.println("onNext " + item);
        this.subscription.request(1);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onError");
      }

      @Override
      public void onComplete() {
        System.out.println("onComplete");
      }
    };

    p.subscribe(s);
  }
}
