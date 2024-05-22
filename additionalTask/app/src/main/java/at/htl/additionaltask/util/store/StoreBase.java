package at.htl.additionaltask.util.store;

import java.util.concurrent.CompletionException;
import java.util.function.Consumer;

import at.htl.additionaltask.util.immer.Immer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class StoreBase<T> {
    public final BehaviorSubject<T> pipe;
    public final Immer<T> immer;

    protected StoreBase(Class<? extends T> type, T initialState) {
        try {
            pipe = BehaviorSubject.createDefault(initialState);
            immer = new Immer<T>(type);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    public void apply(Consumer<T> recipe) {
        Consumer<T> onNext = nextState -> pipe.onNext(nextState);
        immer.produce(pipe.getValue(), recipe, onNext);
    }
}
