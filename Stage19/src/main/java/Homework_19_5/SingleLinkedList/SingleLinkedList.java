package Homework_19_5.SingleLinkedList;

/**
 * Класс SingleLinkedList.
 * Класс для работы с односвязанным списком.
 */
public class SingleLinkedList {
    private SingleListNode topNode;

    /**
     * Метод push.
     * Доавбление элемента в список.
     * @param node элемент списка.
     */
    public void push(SingleListNode node) {
        if (topNode != null) {
            node.setNext(topNode);
        }
        topNode = node;
    }

    /**
     * Метод pop().
     * Извлечение элемента из списка.
     * @return элемент списка.
     */
    public SingleListNode pop() {
        SingleListNode node = topNode;
        if (topNode != null) {
            topNode = topNode.getNext();
            node.setNext(null);
        }
        return node;
    }

    /**
     * Метод removeTop().
     * Удаление верхнего элемента списка.
     */
    public void removeTop() {
        if (topNode != null) {
            topNode = topNode.getNext();
        }
    }

    /**
     * Метод removeLast().
     * Удаление последнего элемента списка.
     */
    public void removeLast() {
        if (topNode != null) {
            if (topNode.getNext() != null) {
                SingleListNode previous = topNode;
                SingleListNode current = topNode.getNext();
                while (current.getNext() != null) {
                    previous = current;
                    current = current.getNext();
                }
                previous.setNext(null);
            }
        }
    }
}
