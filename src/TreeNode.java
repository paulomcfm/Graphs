import java.util.ArrayList;

public class TreeNode {
    private String name;
    private int accessOrder;
    private int smallerAccessOrderNeighbour;
    private int smallerValueNeighbour;
    private ArrayList<TreeNode> children;

    public TreeNode(String name, int accessOrder) {
        this.name = name;
        this.accessOrder = accessOrder;
        this.smallerAccessOrderNeighbour = 0;
        this.smallerValueNeighbour = 0;
        this.children = new ArrayList<>();
    }

    public int getAccessOrder() {
        return accessOrder;
    }

    public void setAccessOrder(int accessOrder) {
        this.accessOrder = accessOrder;
    }

    public int getSmallerAccessOrderNeighbour() {
        return smallerAccessOrderNeighbour;
    }

    public void setSmallerAccessOrderNeighbour(int smallerAccessOrderNeighbour) {
        this.smallerAccessOrderNeighbour = smallerAccessOrderNeighbour;
    }

    public int getSmallerValueNeighbour() {
        return smallerValueNeighbour;
    }

    public void setSmallerValueNeighbour(int smallerValueNeighbour) {
        this.smallerValueNeighbour = smallerValueNeighbour;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TreeNode> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

    public TreeNode getChild(int index) {
        return this.children.get(index);
    }
}
