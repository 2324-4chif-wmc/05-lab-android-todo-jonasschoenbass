package at.htl.additionaltask.model.todo;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import at.htl.additionaltask.model.Store;
import at.htl.additionaltask.util.Config;
import at.htl.additionaltask.util.resteasy.RestApiClientBuilder;

public class TodoService {

    public final String Tag = TodoService.class.getName();

    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";

    public TodoClient todoClient;
    public Store store;

    @Inject
    TodoService(RestApiClientBuilder builder, Store store) {
        Log.i(Tag, "Creating URL: " + JSON_PLACEHOLDER_BASE_URL);
        todoClient= builder.build(TodoClient.class, JSON_PLACEHOLDER_BASE_URL);
        this.store = store;

    }


    public void getAll(){
        CompletableFuture
                .supplyAsync(() -> todoClient.all())
                .thenAccept((x)-> store.setTodos(x))
                .exceptionally((e) -> {
                    Log.e(Tag, "Error loading todos", e);
                    return null;
                });

    }
}
