package Homework_19_5.DoubleLinkedList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс DoubleListNode.
 * Элемент двусвязанного списка.
 */
@Data
class DoubleListNode {

    private String data;
    private DoubleListNode prev;
    private DoubleListNode next;

    public DoubleListNode(String data) {
        this.data = data;
    }
}
