package behavioral.state;

public class ApprovedState implements State {

    @Override
    public void handleRequest() {
        System.out.println("Approved");
    }
}
