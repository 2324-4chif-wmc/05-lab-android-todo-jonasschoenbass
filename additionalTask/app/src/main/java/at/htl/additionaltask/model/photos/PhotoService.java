package at.htl.additionaltask.model.photos;

import android.util.Log;

import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import at.htl.additionaltask.model.Store;
import at.htl.additionaltask.util.resteasy.RestApiClientBuilder;

public class PhotoService {

    public static String Tag = PhotoService.class.getName();

    public static String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";

    public PhotoClient photosClient;
    public Store store;

    @Inject
    PhotoService(RestApiClientBuilder builder, Store store){
        Log.i(Tag, "Created Url " + JSON_PLACEHOLDER_BASE_URL);
        photosClient = builder.build(PhotoClient.class ,JSON_PLACEHOLDER_BASE_URL);
        this.store = store;
    }

    public void getAll(){
        CompletableFuture
                .supplyAsync(() -> photosClient.all())
                .thenAccept((x)-> store.setPhotos(x))
                .exceptionally(x->{
                    Log.i(Tag,"Error loading Photos");
                    return null;
                });
    }

}
