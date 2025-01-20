package com.lcaohoanq.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> users = Arrays.asList(
        new User(1L, "John Doe", "john@example.com"),
        new User(2L, "Jane Smith", "jane@example.com"),
        new User(3L, "Bob Wilson", "bob@example.com")
    );

    // Return all users as an Observable
    public Observable<User> getAllUsers() {
        return Observable.fromIterable(users)
            .delay(100, TimeUnit.MILLISECONDS); // Simulate network delay
    }

    // Find user by ID using Single
    public Single<User> getUserById(Long id) {
        return Observable.fromIterable(users)
            .filter(user -> user.id().equals(id))
            .firstOrError()
            .delay(200, TimeUnit.MILLISECONDS); // Simulate network delay
    }

    // Process user data with various operators
    public Observable<String> processUserData() {
        return getAllUsers()
            .map(user -> user.name().toUpperCase())
            .filter(name -> name.length() > 8)
            .distinct()
            .doOnNext(name -> System.out.println("Processing: " + name));
    }

    // Demonstrate error handling
    public Single<User> getUserWithErrorHandling(Long id) {
        return getUserById(id)
            .onErrorResumeNext(error -> {
                System.err.println("Error occurred: " + error.getMessage());
                return Single.just(new User(0L, "Default User", "default@example.com"));
            });
    }

    // Combine data from multiple sources
    public Observable<String> getCombinedUserData() {
        Observable<String> names = getAllUsers()
            .map(User::name);

        Observable<String> emails = getAllUsers()
            .map(User::email);

        return Observable.zip(names, emails,
                              (name, email) -> String.format("%s (%s)", name, email));
    }
}
