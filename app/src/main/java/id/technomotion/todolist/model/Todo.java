package id.technomotion.todolist.model;

/**
 * Created by omayib on 25/02/16.
 */
public class Todo {
    public final String id;
    public final String item;

    public Todo(String id, String item) {
        this.id = id;
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        return !(id != null ? !id.equals(todo.id) : todo.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
