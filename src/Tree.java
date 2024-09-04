import java.util.ArrayList;
import java.util.Stack;

public class Tree {
    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void displayTree() {
        if (root != null) {
            displayNode(root, 0);
        } else {
            System.out.println("Tree is empty");
        }
    }

    private void displayNode(TreeNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getName() + "[" + node.getAccessOrder() + ","
                + node.getSmallerAccessOrderNeighboursNotOnTree() + ","
                + node.getSmallerValueNeighbour() + "]");
        for (TreeNode child : node.getChildren()) {
            displayNode(child, depth + 1);
        }
    }

    public void generateTree(MatrizAdjacencia ma) {
        int[][] matriz = ma.getMatriz();
        String[] vertices = ma.getVertices();
        this.root = new TreeNode(vertices[0], 1);
        int order = 2;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = this.root;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i!=j && matriz[i][j] != 0) {
                    TreeNode newNode = findNode(vertices[j]);
                    if (newNode == null) {
                        node.addChild(new TreeNode(vertices[j], order++));
                        stack.push(node);
                        //pegar o ultimo filho do node
                        node = node.getChildren().get(node.getChildren().size()-1);
                        i=j-1;
                        j= matriz.length;
                    }
                }
                if(j+1==matriz[i].length) {
                    if(!stack.isEmpty())
                        node = stack.pop();
                    i=findVertex(node.getName(), vertices)-1;
                }
            }
            if(treeSize()==matriz.length) {
                i=matriz.length;
            }
        }
        System.out.println("Árvore gerada com valores prenum:");
        displayTree();
        fillSmallerAccessOrderNeighbourNotOnTree(ma);
        System.out.println("Árvore com valores prenum do vértice que está ligado no grafo mas não na árvore:");
        displayTree();
        fillSmallerValueNeighbour();
        System.out.println("Árvore com valores menor valor dos vértices filhos:");
        displayTree();
        ArrayList<TreeNode> articulationPoints = findArticulationPoints();
        System.out.println("Pontos de articulação/Componentes cruciais para as funções biológicas:");
        for(TreeNode articulationPoint : articulationPoints) {
            System.out.println(articulationPoint.getName());
        }
    }

    private ArrayList<TreeNode> findArticulationPoints() {
        ArrayList<TreeNode> articulationPoints = new ArrayList<>();
        TreeNode node = this.root;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> check = new Stack<>();
        stack.push(node);
        check = postOrder(node, check);
        if(node.getChildren().size()>1)
            check.push(node);
        while(!check.isEmpty()) {
            node = check.pop();
            for(TreeNode child : node.getChildren()) {
                if(node.getAccessOrder() <= getSmallerValue(child, 0) && !articulationPoints.contains(node)) {
                    articulationPoints.add(node);
                }
            }
        }
        return articulationPoints;
    }

    private void fillSmallerValueNeighbour() {
        Stack <TreeNode> stack = new Stack<>();
        stack.push(this.root);
        stack = postOrder(this.root, stack);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int smaller = 0;
            for (TreeNode child : node.getChildren()) {
                smaller = getSmallerValue(child, smaller);
            }
            node.setSmallerValueNeighbour(smaller);
        }
    }

    private int getSmallerValue(TreeNode child, int smaller) {
        if(smaller == 0)
            smaller = child.getAccessOrder();
        if(child.getSmallerAccessOrderNeighboursNotOnTree()!=0 && child.getSmallerAccessOrderNeighboursNotOnTree() < smaller)
            smaller = child.getSmallerAccessOrderNeighboursNotOnTree();
        if(child.getAccessOrder() != 0 && child.getAccessOrder()<smaller)
            smaller = child.getAccessOrder();
        if(child.getSmallerValueNeighbour() != 0 && child.getSmallerValueNeighbour()<smaller)
            smaller = child.getSmallerValueNeighbour();
        return smaller;
    }

    private Stack<TreeNode> postOrder(TreeNode root, Stack<TreeNode> stack) {
        for (TreeNode child : root.getChildren()) {
            if(child.getChildren().size() > 0) {
                stack.push(child);
            }
            postOrder(child, stack);
        }
        return stack;
    }

    private void fillSmallerAccessOrderNeighbourNotOnTree(MatrizAdjacencia ma) {
        int smaller=0;
        int[][] matriz = ma.getMatriz();
        String[] vertices = ma.getVertices();
        for(int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i!=j && matriz[i][j] != 0) {
                    if(!isConnected(vertices[i], vertices[j]) && !isConnected(vertices[j], vertices[i])){
                        TreeNode child = findNode(vertices[j]);
                        if(smaller == 0)
                            smaller = child.getAccessOrder();
                        else{
                            if(child.getAccessOrder() < smaller)
                                smaller = child.getAccessOrder();
                        }
                    }
                }
            }
            TreeNode node = findNode(vertices[i]);
            node.setSmallerAccessOrderNeighboursNotOnTree(smaller);
            smaller=0;
        }
    }

    private boolean isConnected(String parent, String child) {
        TreeNode node = findNode(parent);
        for (TreeNode childNode : node.getChildren()) {
            if (childNode.getName().equals(child)) {
                return true;
            }
        }
        return false;
    }

    private void printTree() {
        TreeNode node = this.root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node.getName()+" "+node.getAccessOrder());
            for (TreeNode child : node.getChildren()) {
                stack.push(child);
            }
        }
    }

    private int findVertex(String name, String[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private TreeNode findNode(String vertex) {
        TreeNode node = this.root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            node = stack.pop();
            if (node.getName().equals(vertex)) {
                return node;
            }
            for (TreeNode child : node.getChildren()) {
                stack.push(child);
            }
        }
        return null;
    }

    private int treeSize() {
        TreeNode node = this.root;
        Stack<TreeNode> stack = new Stack<>();
        int size = 1;
        stack.push(node);
        while(!stack.isEmpty()) {
            node = stack.pop();
            for (TreeNode child : node.getChildren()) {
                stack.push(child);
                size++;
            }
        }
        return size;
    }
}
