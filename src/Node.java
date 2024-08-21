public class Node {
    private Node next;
    private Node aim;
    private char value;

    public Node(char value) {
        this.value = value;
    }

    public Node() {
        this(' ');
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
