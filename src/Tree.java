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

    public void generateTree(MatrizAdjacencia ma) {
        int[][] matriz = ma.getMatriz();
        String[] vertices = ma.getVertices();
        this.root = new TreeNode(vertices[0], 1);
        int order = 2;
        TreeNode before = null;
        TreeNode node = this.root;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i!=j && matriz[i][j] != 0) {
                    TreeNode newNode = findNode(vertices[j]);
                    if (newNode == null) {
                        node.addChild(new TreeNode(vertices[j], order++));
                        before = node;
                        node = node.getChild(0);
                        i=j-1;
                        j= matriz.length;
                    }
                }
                if(j+1==matriz[i].length) {
                    node = before;
                    i=findVertex(node.getName(), vertices)-1;
                }
            }
            if(treeSize()==matriz.length) {
                i=matriz.length;
            }
        }
        printTree();
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
