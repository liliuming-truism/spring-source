package top.truism.blog.beanfactory;

public class SimpleService {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void showMessage() {
        System.out.println("Message: " + message);
    }
}