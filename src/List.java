import java.io.BufferedReader;
import java.io.IOException;

public class List {
    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

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

    void addAim(char value, int distance, int node) {
        Node current = this.head;
        for (int i = 0; i < node; i++) {
            current = current.getNext();
        }
        Node newNode = new Node(value);
        newNode.setAim(current.getAim());
        current.setAim(newNode);
    }

    void addNext(char value, int distance) {
        Node newNode = new Node(value);
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

    public boolean isDigrafo() {
        Node node = this.head;
        while (node != null) {
            Node aim = node.getAim();
            while (aim != null) {
                if(!searchConnection(aim.getValue(),node.getValue())) {
                    return true;
                }
                aim = aim.getAim();
            }
            node = node.getNext();
        }
        return false;
    }

    private boolean searchConnection(char valueAim, char valueNode) {
        Node current = this.head;
        while (current != null) {
            if (current.getValue() == valueAim) {
                Node aim = current.getAim();
                while (aim != null) {
                    if (aim.getValue() == valueNode) {
                        return true;
                    }
                    aim = aim.getAim();
                }
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean isSimples() {
        Node current = this.head;
        while (current != null) {
            Node aim = current.getAim();
            while (aim != null) {
                if (current.getValue() == aim.getValue()) {
                    return false;
                }
                aim = aim.getAim();
            }
            current = current.getNext();
        }
        return true;
    }

    public boolean isRegularEmissao() {
        Node node = this.head;
        int grau = 0;

        Node aim = node.getAim();
        while (aim != null) {
            grau++;
            aim = aim.getAim();
        }

        node = node.getNext();
        while (node != null) {
            int grauVertice = 0;
            aim = node.getAim();
            while (aim != null) {
                grauVertice++;
                aim = aim.getAim();
            }
            if (grauVertice != grau) {
                return false;
            }
            node = node.getNext();
        }
        return true;
    }

    public boolean isRegularRecepcao() {
        Node node = this.head;
        int grau = countRecieving(node.getValue());
        node = node.getNext();
        while (node != null) {
            int grauVertice = countRecieving(node.getValue());
            if (grauVertice != grau) {
                return false;
            }
            node = node.getNext();
        }
        return true;
    }

    private int countRecieving(char value) {
        Node node = this.head;
        int grau = 0;
        while (node != null) {
            Node aim = node.getAim();
            while (aim != null) {
                if (aim.getValue() == value) {
                    grau++;
                }
                aim = aim.getAim();
            }
            node = node.getNext();
        }
        return grau;
    }

    public boolean isRegular() {
        Node node = this.head;
        int grau = 0;
        Node aim = node.getAim();
        while (aim != null) {
            grau++;
            aim = aim.getAim();
        }
        node = node.getNext();
        while (node != null) {
            int grauVertice = 0;
            aim = node.getAim();
            while (aim != null) {
                grauVertice++;
                aim = aim.getAim();
            }
            if (grauVertice != grau) {
                return false;
            }
            node = node.getNext();
        }
        return true;
    }

    public boolean isCompleto() {
        //conta quantos vertices tem, depois conta quantas arestas cada vertice tem, se todas os vertices tiverem o grau = n-1, entao eh completo
        int n = 0;
        Node current = this.head;
        while (current != null) {
            n++;
            current = current.getNext();
        }
        current = this.head;
        while (current != null) {
            int grau = 0;
            Node aim = current.getAim();
            while (aim != null) {
                grau++;
                aim = aim.getAim();
            }
            if (grau != n-1) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }
}
