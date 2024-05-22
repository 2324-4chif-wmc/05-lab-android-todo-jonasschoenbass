package at.htl.additionaltask.model.post;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import at.htl.additionaltask.model.Model;
import at.htl.additionaltask.model.Store;
import at.htl.additionaltask.util.resteasy.RestApiClientBuilder;

public class PostService {
    public final String Tag = PostService.class.getName();

    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";

    public PostClient postClient;
    public Store store;

    @Inject
    PostService(RestApiClientBuilder builder, Store store){
        Log.i(Tag, "Created Url" + JSON_PLACEHOLDER_BASE_URL);
        postClient = builder.build(PostClient.class , JSON_PLACEHOLDER_BASE_URL);
        this.store = store;
    }

    public void getAll(){
        CompletableFuture
                .supplyAsync(()->postClient.all())
                .thenAccept(x->store.setPosts(x))
                .exceptionally((e) -> {
                    Log.e(Tag, "Error loading todos", e);
                    return null;
                });
    }

    public void createPost(PostDto postDto, int id){
        if (postDto.title() == ""||postDto.body() == ""){
            return;
        }

        CompletableFuture
                .supplyAsync(()->postClient.create(postDto))
//                .thenAccept((x)->postClient.all())
//                .thenAccept((x)->store.setPosts(x))
                //Kann ich nicht machen, da der Server nicht aktuallisiert wird
                .exceptionally((e) -> {
                    Log.e(Tag, "Error loading todos", e);
                    return null;
                });




        Post post = new Post();
        post.userId = postDto.userId();
        post.title = postDto.title();
        post.body = postDto.body();
        post.id = (long) (id + 1);
        //Ich muss die ID so übergeben, da mann nicht das Model Injecten kann und wie gesagt ich mir nciht die Id über den Rest holen kann.

        store.addPost(post);
    }

}
