package Homework_19_5.SingleLinkedList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс SingleListNode
 * Элемент односвязного списка.
 */
@Data
public class SingleListNode {

    private String data;
    private SingleListNode next;

    public SingleListNode(String data) {
        this.data = data;
    }
}
