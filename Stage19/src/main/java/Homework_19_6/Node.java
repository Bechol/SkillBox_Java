package Homework_19_6;

import lombok.Data;

/**
 * Класс Node.
 * Элемент бинарного дерева.
 */
@Data
public class Node {
    private String data;

    private Node parent;
    private Node left;
    private Node right;

    public Node(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String parentData = parent != null ? parent.getData() : null;
        String leftData = left != null ? left.getData() : null;
        String rightData = right != null ? right.getData() : null;
        return "Node{" +
                "data='" + data + '\'' +
                ", parent=" + parentData +
                ", left=" + leftData +
                ", right=" + rightData +
                '}';
    }

}