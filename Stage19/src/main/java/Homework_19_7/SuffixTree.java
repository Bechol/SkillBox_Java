package Homework_19_7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SuffixTree {
    private static final String WORD_TERMINATION = "$";
    private static final int POSITION_UNDEFINED = -1;
    private final Node root;
    private final String fullText;

    public SuffixTree(String text) {
        root = new Node("", POSITION_UNDEFINED);
        for (int i = 0; i < text.length(); i++) {
            addSuffix(text.substring(i) + WORD_TERMINATION, i);
        }
        fullText = text;
    }

    public List<Integer> searchPositions(String pattern) {

        List<Node> nodes = getAllNodesInTraversePath(pattern, root, false);
        List<Integer> positions = null;
        List<Integer> result = new ArrayList<>();

        if (nodes.size() > 0) {
            Node lastNode = nodes.get(nodes.size() - 1);
            if (lastNode != null) {
                positions = getPositions(lastNode);
            }
            if (positions != null) {
                result = new ArrayList<>(positions);
                result.sort(Comparator.naturalOrder());
            }
        }
        return result;
    }

    private void addSuffix(String suffix, int position) {
        List<Node> nodes = getAllNodesInTraversePath(suffix, root, true);
        if (nodes.size() == 0) {
            addChildNode(root, suffix, position);
        } else {
            Node lastNode = nodes.remove(nodes.size() - 1);
            String newText = suffix;
            if (nodes.size() > 0) {
                String existingSuffixUpToLastNode = nodes.stream()
                        .map(Node::getText)
                        .reduce("", String::concat);

                // Remove prefix from newText already included in parent
                newText = newText.substring(existingSuffixUpToLastNode.length());
            }
            extendNode(lastNode, newText, position);
        }
    }

    private List<Integer> getPositions(Node node) {
        List<Integer> positions = new ArrayList<>();
        if (node.getText()
                .endsWith(WORD_TERMINATION)) {
            positions.add(node.getPosition());
        }
        for (int i = 0; i < node.getChildren()
                .size(); i++) {
            positions.addAll(getPositions(node.getChildren()
                    .get(i)));
        }
        return positions;
    }

    private void addChildNode(Node parentNode, String text, int position) {
        parentNode.getChildren()
                .add(new Node(text, position));
    }

    private void extendNode(Node node, String newText, int position) {
        String currentText = node.getText();
        String commonPrefix = getLongestCommonPrefix(currentText, newText);

        if (commonPrefix != currentText) {
            String parentText = currentText.substring(0, commonPrefix.length());
            String childText = currentText.substring(commonPrefix.length());
            splitNodeToParentAndChild(node, parentText, childText);
        }

        String remainingText = newText.substring(commonPrefix.length());
        addChildNode(node, remainingText, position);
    }

    private void splitNodeToParentAndChild(Node parentNode, String parentNewText, String childNewText) {
        Node childNode = new Node(childNewText, parentNode.getPosition());

        if (parentNode.getChildren()
                .size() > 0) {
            while (parentNode.getChildren()
                    .size() > 0) {
                childNode.getChildren()
                        .add(parentNode.getChildren()
                                .remove(0));
            }
        }

        parentNode.getChildren()
                .add(childNode);
        parentNode.setText(parentNewText);
        parentNode.setPosition(POSITION_UNDEFINED);
    }


    private String getLongestCommonPrefix(String str1, String str2) {
        int compareLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < compareLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, compareLength);
    }

    private List<Node> getAllNodesInTraversePath(String pattern, Node startNode, boolean isAllowPartialMatch) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < startNode.getChildren()
                .size(); i++) {
            Node currentNode = startNode.getChildren()
                    .get(i);
            String nodeText = currentNode.getText();
            if (pattern.charAt(0) == nodeText.charAt(0)) {
                if (isAllowPartialMatch && pattern.length() <= nodeText.length()) {
                    nodes.add(currentNode);
                    return nodes;
                }

                int compareLength = Math.min(nodeText.length(), pattern.length());
                for (int j = 1; j < compareLength; j++) {
                    if (pattern.charAt(j) != nodeText.charAt(j)) {
                        if (isAllowPartialMatch) {
                            nodes.add(currentNode);
                        }
                        return nodes;
                    }
                }

                nodes.add(currentNode);
                if (pattern.length() > compareLength) {
                    List<Node> nodes2 = getAllNodesInTraversePath(pattern.substring(compareLength), currentNode, isAllowPartialMatch);
                    if (nodes2.size() > 0) {
                        nodes.addAll(nodes2);
                    } else if (!isAllowPartialMatch) {
                        nodes.add(null);
                    }
                }
                return nodes;
            }
        }
        return nodes;
    }
}