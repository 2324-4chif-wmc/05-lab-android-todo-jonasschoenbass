package at.htl.additionaltask.model;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import at.htl.additionaltask.model.photos.Photo;
import at.htl.additionaltask.model.post.Post;
import at.htl.additionaltask.model.todo.Todo;
import at.htl.additionaltask.util.store.StoreBase;

@Singleton
public class Store extends StoreBase<Model> {

    @Inject
    Store() {
        super(Model.class, new Model());
    }

    public void setTodos(Todo[] todos) {
        apply(model -> model.todos = todos);
    }

    public void selectTab(int tabIndex) {
        apply(model -> model.uiState.selectedTab = tabIndex);
    }

    public void setCheckboxTodo(Todo todo){
        int id = (int) (todo.id - 1);
        apply(model -> model.todos[id].completed = !todo.completed);
    }

    public void  setPhotos(Photo[] photos){
        apply(model -> model.photos = photos);
    }

    public void setPictureAmount(int amount){
        apply(model -> model.uiState.amountLoadPictures = amount);
    }

    public void setPosts (List<Post> posts){
        apply(model -> model.posts = posts);
    }
    public void addPost(Post post) {
      /*  apply(model ->{
            Post[] newPosts = new Post[model.posts.length + 1];
            System.arraycopy(model.posts, 0, newPosts, 0, model.posts.length);
            newPosts[model.posts.length] = post;
            model.posts = newPosts;
        });*/

        apply(model -> model.posts.add(post));
    }

}
