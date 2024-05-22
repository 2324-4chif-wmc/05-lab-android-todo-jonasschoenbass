package at.htl.additionaltask.model;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import at.htl.additionaltask.model.photos.Photo;
import at.htl.additionaltask.model.post.Post;
import at.htl.additionaltask.model.todo.Todo;

public class Model {
    public static class UIState{
        public int selectedTab = 0;
        public int amountLoadPictures = 3;
    }

    public UIState uiState = new UIState();
    public Todo[] todos = new Todo[0];
    public Photo[] photos = new Photo[0];
    public List<Post> posts = List.of();

}
