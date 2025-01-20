# Key RxJava Types Used:
- Observable: For streaming multiple items
- Single: For single-item responses

# Key RxJava Operators Demonstrated:

- map: Transform data
- filter: Filter data based on conditions
- zip: Combine multiple streams
- delay: Add artificial delays
- toList: Collect results
- blockingGet: Convert to synchronous calls (for demonstration)

# RxJava Complete Guide with Examples

## Table of Contents
- [Core Types](#core-types)
- [Creation Operators](#creation-operators)
- [Transformation Operators](#transformation-operators)
- [Filtering Operators](#filtering-operators)
- [Combining Operators](#combining-operators)
- [Error Handling](#error-handling)
- [Utility Operators](#utility-operators)
- [Backpressure Handling](#backpressure-handling)
- [Concurrency and Schedulers](#concurrency-and-schedulers)
- [Testing](#testing)

## Core Types

### Observable
Emits 0 to N items and terminates with either completion or error.

```java
// Basic Observable
Observable<String> observable = Observable.just("Hello", "RxJava");
observable.subscribe(
    item -> System.out.println(item),
    error -> error.printStackTrace(),
    () -> System.out.println("Completed")
);

// Observable with multiple items
Observable<Integer> numbers = Observable.range(1, 5);
numbers.subscribe(System.out::println);
```

### Single
Emits exactly one item or an error.

```java
Single<String> single = Single.just("Single Item");
single.subscribe(
    success -> System.out.println(success),
    error -> error.printStackTrace()
);
```

### Maybe
Emits 0 or 1 item, or an error.

```java
Maybe<String> maybe = Maybe.just("Maybe Item");
maybe.subscribe(
    success -> System.out.println(success),
    error -> error.printStackTrace(),
    () -> System.out.println("Completed empty")
);
```

### Completable
Only concerned with completion or error, no items.

```java
Completable completable = Completable.complete();
completable.subscribe(
    () -> System.out.println("Completed"),
    error -> error.printStackTrace()
);
```

## Creation Operators

### just()
Creates an Observable from a set of items.

```java
Observable<String> observable = Observable.just("One", "Two", "Three");
```

### fromIterable()
Creates an Observable from an Iterable.

```java
List<String> list = Arrays.asList("A", "B", "C");
Observable<String> observable = Observable.fromIterable(list);
```

### create()
Creates an Observable programmatically.

```java
Observable<String> observable = Observable.create(emitter -> {
    try {
        emitter.onNext("Item 1");
        emitter.onNext("Item 2");
        emitter.onComplete();
    } catch (Exception e) {
        emitter.onError(e);
    }
});
```

### interval()
Creates an Observable that emits items periodically.

```java
Observable<Long> timer = Observable.interval(1, TimeUnit.SECONDS);
timer.subscribe(i -> System.out.println("Tick: " + i));
```

## Transformation Operators

### map()
Transforms each item emitted.

```java
Observable.just("Hello", "World")
    .map(String::toUpperCase)
    .subscribe(System.out::println);
```

### flatMap()
Transforms items and flattens the results.

```java
Observable.just("1", "2", "3")
    .flatMap(s -> Observable.just(s + "a", s + "b"))
    .subscribe(System.out::println);
```

### switchMap()
Like flatMap but cancels previous subscriptions.

```java
Observable.interval(1, TimeUnit.SECONDS)
    .switchMap(i -> Observable.just("Value: " + i)
        .delay(500, TimeUnit.MILLISECONDS))
    .subscribe(System.out::println);
```

### scan()
Applies a function to each item with the result of previous operations.

```java
Observable.range(1, 5)
    .scan((acc, item) -> acc + item)
    .subscribe(System.out::println);
```

## Filtering Operators

### filter()
Filters items based on a predicate.

```java
Observable.range(1, 10)
    .filter(n -> n % 2 == 0)
    .subscribe(System.out::println);
```

### distinct()
Suppresses duplicate items.

```java
Observable.just(1, 1, 2, 2, 3)
    .distinct()
    .subscribe(System.out::println);
```

### take()
Takes only the first N items.

```java
Observable.range(1, 100)
    .take(5)
    .subscribe(System.out::println);
```

### skip()
Skips the first N items.

```java
Observable.range(1, 10)
    .skip(5)
    .subscribe(System.out::println);
```

## Combining Operators

### merge()
Combines multiple Observables into one.

```java
Observable<String> obs1 = Observable.just("A", "B");
Observable<String> obs2 = Observable.just("C", "D");
Observable.merge(obs1, obs2)
    .subscribe(System.out::println);
```

### zip()
Combines items from multiple Observables.

```java
Observable<String> names = Observable.just("John", "Jane");
Observable<Integer> ages = Observable.just(25, 30);
Observable.zip(names, ages, 
    (name, age) -> name + " is " + age)
    .subscribe(System.out::println);
```

### combineLatest()
Combines the latest items from multiple Observables.

```java
Observable<String> obs1 = Observable.interval(1, TimeUnit.SECONDS)
    .map(i -> "First: " + i);
Observable<String> obs2 = Observable.interval(2, TimeUnit.SECONDS)
    .map(i -> "Second: " + i);
Observable.combineLatest(obs1, obs2,
    (first, second) -> first + " - " + second)
    .subscribe(System.out::println);
```

## Error Handling

### onErrorReturn()
Returns a default value on error.

```java
Observable.error(new RuntimeException("Error"))
    .onErrorReturn(error -> "Default Value")
    .subscribe(System.out::println);
```

### retry()
Retries on error.

```java
Observable.error(new RuntimeException("Error"))
    .retry(3)
    .subscribe(
        System.out::println,
        error -> System.out.println("Error after 3 retries")
    );
```

### retryWhen()
Advanced retry logic.

```java
Observable.error(new RuntimeException("Error"))
    .retryWhen(errors -> 
        errors.zipWith(Observable.range(1, 3), (error, count) -> {
            if (count < 3) return count;
            throw error;
        }).flatMap(count -> 
            Observable.timer(count * 1000, TimeUnit.MILLISECONDS))
    )
    .subscribe(System.out::println);
```

## Utility Operators

### delay()
Delays emissions by a specified time.

```java
Observable.just("Delayed Item")
    .delay(1, TimeUnit.SECONDS)
    .subscribe(System.out::println);
```

### timeout()
Errors if items aren't emitted within specified time.

```java
Observable.just("Item")
    .delay(2, TimeUnit.SECONDS)
    .timeout(1, TimeUnit.SECONDS)
    .onErrorReturn(error -> "Timeout occurred")
    .subscribe(System.out::println);
```

### doOnNext(), doOnError(), doOnComplete()
Side effects for notifications.

```java
Observable.just("Item")
    .doOnNext(item -> System.out.println("Processing: " + item))
    .doOnError(error -> System.out.println("Error: " + error))
    .doOnComplete(() -> System.out.println("Completed"))
    .subscribe();
```

## Concurrency and Schedulers

### subscribeOn()
Specifies scheduler for subscription.

```java
Observable.just("Item")
    .subscribeOn(Schedulers.io())
    .subscribe(System.out::println);
```

### observeOn()
Specifies scheduler for observers.

```java
Observable.just("Background Work")
    .subscribeOn(Schedulers.io())
    .map(s -> s + " Processed")
    .observeOn(Schedulers.computation())
    .subscribe(System.out::println);
```

## Real-World Example

### User Service with RxJava

```java
public class UserService {
    private final UserRepository repository;
    
    public Observable<User> getUserWithOrders(Long userId) {
        return Observable.defer(() -> 
            Observable.just(repository.findById(userId))
                .flatMap(user -> 
                    Observable.zip(
                        Observable.just(user),
                        getOrdersForUser(user.getId()),
                        (u, orders) -> {
                            u.setOrders(orders);
                            return u;
                        }
                    )
                )
                .subscribeOn(Schedulers.io())
                .doOnNext(user -> logUserAccess(user))
                .retry(3)
                .timeout(5, TimeUnit.SECONDS)
                .onErrorResumeNext(error -> 
                    Observable.just(new User(userId, "Default User")))
        );
    }
    
    private Observable<List<Order>> getOrdersForUser(Long userId) {
        return Observable.defer(() -> 
            Observable.just(repository.findOrdersByUserId(userId))
        ).subscribeOn(Schedulers.io());
    }
}
```

This example combines multiple RxJava features:
- Deferred execution
- Error handling with retry and fallback
- Concurrent execution with schedulers
- Combining streams with zip
- Timeout handling
- Side effects with doOnNext