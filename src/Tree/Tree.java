package Tree;

class Node {
    Node rightChild;
    Node leftChild;
    int data;

    public Node(int data) {
        this.data = data;
    }
}

public class Tree {
    Node root;

    public void insert(int data) {
        Node newNode = new Node(data);

        if (root == null)
            root = newNode;
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (current.data > newNode.data) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // Симметричный обход:
    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);

            System.out.println(localRoot.data + " ");
            inOrder(localRoot.rightChild);
        }
    }

    public Node find(int data) {
        Node current = root;
        if (current == null) return current;
        while (current.data != data) {
            if (data < current.data) {
                current = current.leftChild;
            } else
                current = current.rightChild;
            if (current == null) return null;
        }
        return current;
    }

    public void delete(int data) {
        Node current = root, parent;
        if (root == null) return;
        while (current.data != data) {
            if (data < current.data) {
                parent = current;
                current = current.leftChild;
            } else {
                parent = current;
                current = current.rightChild;
            }
        }
        current = null;
    }

    public boolean del(int data) {
        Node current = root, parent = current;
        boolean isLeftChild = true;

        while (current.data != data) {
            parent = current;
            if (data < current.data) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null)
                return false;
        }

        //если узел является листом (не имющим потомков)
        if (current.leftChild == null && current.rightChild == null)
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;

        // код ниже при удалении узла с одним потомком.
        if (current.rightChild == null)
            if (current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        else if (current.leftChild == null)
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        else {
            Node successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            successor.leftChild = current.leftChild;
        }
        return true;
    }

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
}


