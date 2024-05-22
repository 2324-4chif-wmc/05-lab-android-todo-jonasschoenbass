package at.htl.additionaltask.model;


import at.htl.additionaltask.model.photos.Photo;
import at.htl.additionaltask.model.todo.Todo;

public class Model {
    public static class UIState{
        public int selectedTab = 0;
        public int amountLoadPictures = 3;
    }

    public UIState uiState = new UIState();
    public Todo[] todos = new Todo[0];
    public Photo[] photos = new Photo[0];

}
