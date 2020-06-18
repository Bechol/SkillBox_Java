package Homework_19_6;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс BinaryTree.
 * Методы для работы с бинарным деревом.
 */
public class BinaryTree
{
    private Node root;

    /**
     * Метод addNode.
     * Добавление элемента.
     * @param data данные для добавления.
     */
    public void addNode(String data)
    {
        if (root == null) {
            root = new Node(data);
        } else {
            Node parent = root;
            while (true) {
                if (data.compareTo(parent.getData()) < 0) {
                    if (parent.getLeft() == null) {
                        Node newNode = new Node(data);
                        newNode.setParent(parent);
                        parent.setLeft(newNode);
                        break;
                    } else {
                        parent = parent.getLeft();
                    }
                } else {
                    if (parent.getRight() == null) {
                        Node newNode = new Node(data);
                        newNode.setParent(parent);
                        parent.setRight(newNode);
                        break;
                    } else {
                        parent = parent.getRight();
                    }
                }
            }
        }
    }

    /**
     * Метод searchNodes.
     * Поиск элементов дерева.
     * @param data искомое значениею.
     * @return список значений.
     */
    public List<Node> searchNodes(String data)
    {
        List<Node> resultList = new ArrayList<>();
        if (root != null) {
            Node parent = root;
            while (true) {
                if (data.compareTo(parent.getData()) < 0) {
                    Node left = parent.getLeft();
                    if (left == null) {
                        break;
                    } else {
                        parent = left;
                        continue;
                    }
                }
                if (data.compareTo(parent.getData()) >= 0) {
                    if (data.equals(parent.getData())) resultList.add(parent);
                    Node right = parent.getRight();
                    if (right == null) {
                        break;
                    } else {
                        parent = right;
                    }
                }
            }
        }
        return resultList;
    }
}