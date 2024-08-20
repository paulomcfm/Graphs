import java.io.BufferedReader;
import java.io.IOException;

public class List {
    private Node head;

    public void fillList(BufferedReader br) throws IOException {
        String line = br.readLine();
        int j=0;
        if (line != null) {
            String[] vertices = line.split(",");
            for (String vertex : vertices) {
                addNext(vertex.charAt(0),0);
            }
            line = br.readLine();
            while (line != null) {
                String[] values = line.split(",");
                for(int i = 0; i < values.length; i++) {
                    if (!values[i].equals("0")) {
                        addAim(vertices[i].charAt(0), 0,j);
                    }
                }
                j++;
                line = br.readLine();
            }
        }
    }

    private void addAim(char value, int distance, int node) {
        Node current = this.head;
        for (int i = 0; i < node; i++) {
            current = current.getNext();
        }
        Node newNode = new Node(value,distance);
        newNode.setAim(current.getAim());
        current.setAim(newNode);
    }

    private void addNext(char value, int distance) {
        Node newNode = new Node(value,distance);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void printList() {
        Node currentHead = head;
        Node currentAim;
        while (currentHead != null) {
            System.out.print(currentHead.getValue()+ " ");
            currentAim = currentHead.getAim();
            while(currentAim != null) {
                System.out.print(currentAim.getValue() + " ");
                currentAim = currentAim.getAim();
            }
            System.out.println();
            currentHead = currentHead.getNext();
        }
        System.out.println();
    }
}
