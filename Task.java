public class Task {

    private int id;
    private String text;
    private boolean done;

    public Task(int id, String text) {
        this.id = id;
        this.text = text;
        done = false;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {
        return done;
    }
}
