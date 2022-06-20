package model.beans;

public class Message {
    private String body;
    private String title;
    private MessageType type;

    public Message(String body, String title, MessageType type) {
        this.body = body;
        this.title = title;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
