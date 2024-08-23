public class Node {
    private Node next;
    private Node aim;
    private int distance;
    private char value;

    public Node(char value, int distance) {
        this.value = value;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Node() {
        this(' ',0);
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getAim() {
        return aim;
    }

    public void setAim(Node aim) {
        this.aim = aim;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
