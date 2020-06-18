package Homework_19_5.DoubleLinkedList;

/**
 * Класс DoubleLinkedList.
 * Методы для работы с двухсвязным списком.
 */
public class DoubleLinkedList {
    private DoubleListNode topNode;
    private DoubleListNode lastNode;

    /**
     * Метод getTopNode.
     * Геттер для topNode.
     *
     * @return верхний элемент списка.
     */
    public DoubleListNode getTopNode() {
        return topNode;
    }

    /**
     * Метод getLastNode.
     * Геттер для lastNode.
     *
     * @return последний элемент списка.
     */
    public DoubleListNode getLastNode() {
        return lastNode;
    }

    /**
     * Метод popTopNode.
     * Извлечение верхнего элемента списка.
     *
     * @return верхний элемент списка.
     */
    public DoubleListNode popTopNode() {
        DoubleListNode node = topNode;
        if (node != null) {
            topNode = topNode.getNext();
            topNode.setPrev(null);
            node.setNext(null);
        }
        return node;
    }

    /**
     * Метод popLastNode.
     * Извлечение последнего элемента списка.
     *
     * @return последний элемент списка.
     */
    public DoubleListNode popLastNode() {
        DoubleListNode node = lastNode;
        if (node != null) {
            lastNode = lastNode.getPrev();
            lastNode.setNext(null);
            node.setPrev(null);
        }
        return node;
    }

    /**
     * Метод removeTopNode.
     * Удаление верхнего элемента списка.
     */
    public void removeTopNode() {
        if (topNode != null) {
            topNode = topNode.getNext();
            topNode.setPrev(null);
        }
    }

    /**
     * Метод removeTopNode.
     * Удаление последнего элемента списка.
     */
    public void removeLastNode() {
        if (lastNode != null) {
            lastNode = lastNode.getPrev();
            lastNode.setNext(null);
        }
    }

    /**
     * Метод addToTop.
     * Добавление элемента на верх.
     * @param node элемент, который хотим добавить.
     */
    public void addToTop(DoubleListNode node) {
        node.setNext(topNode);
        if (topNode != null) {
            topNode.setPrev(node);
            node.setNext(topNode);
            if (topNode.getNext() == null) {
                lastNode = topNode;
            }
        }
        topNode = node;
        if (lastNode == null) {
            lastNode = topNode;
        }
    }
    /**
     * Метод addToLast.
     * Добавление элемента в конец.
     * @param node элемент, который хотим добавить.
     */
    public void addToLast(DoubleListNode node) {
        node.setPrev(lastNode);
        if (lastNode != null) {
            lastNode.setNext(node);
            node.setPrev(lastNode);
            if (lastNode.getPrev() == null) {
                topNode = lastNode;
            }
        }
        lastNode = node;
        if (topNode == null) {
            topNode = lastNode;
        }
    }
}
