public class ThreadedBinarySearchTree {
    private Node root;

    public ThreadedBinarySearchTree() {
        root = null;
    }

    public boolean insert(int data) {
        Node newNode = new Node(data);
        if(newNode == null) {
            return false;
        }

        if(root == null) {
            root = newNode;
            return true;
        }

        Node temp = root;
        while(true) {
            if(data == temp.getData()) {
                return false;
            }

            if(data < temp.getData()) {
                if(temp.getlFlag() == 'T') {
                    newNode.setRight(temp); //step 1
                    newNode.setLeft(temp.getLeft()); //step 2
                    temp.setLeft(newNode); //step 3
                    temp.setlFlag('L'); //step 3
                    return true;
                }
                temp = temp.getLeft();
            }
            else {
                if(temp.getrFlag() == 'T') {
                    newNode.setLeft(temp); // 1
                    newNode.setRight(temp.getRight()); // 2
                    temp.setRight(newNode); // 3
                    temp.setrFlag('L'); // 3
                    return true;
                }
                temp = temp.getRight();
            }
        }
    }

    public void preOrder() {
        Node temp = root;
        char flag = 'L';

        System.out.print("PreOrder: ");
        while(temp != null) {
            while(temp.getlFlag() == 'L' && flag == 'L') {
                System.out.print(temp.getData() + " ");
                temp = temp.getLeft();
            }

            if(flag == 'L') {
                System.out.print(temp.getData() + " ");
            }
            flag = temp.getrFlag();
            temp = temp.getRight();
        }
        System.out.println();

    }

    public void inOrder() {
        Node temp = root;
        char flag = 'L';

        System.out.print("InOrder: ");
        while(temp != null) {
            while(temp.getlFlag() == 'L' && flag == 'L') {
                temp = temp.getLeft();
            }
            System.out.print(temp.getData() + " ");
            flag = temp.getrFlag();
            temp = temp.getRight();
        }
        System.out.println();
    }

    public void postOrder() {
        Node temp =root;
        char flag = 'L';

        System.out.print("PostOrder: ");
        while(temp != null) {
            //check if temp has left child which is unvisited if yes then shift to left of temp
            while(temp.getlFlag() == 'L' && flag == 'L') {
                temp = temp.getLeft();
            }
            //check if temp has a right child
            flag = temp.getrFlag();

            //if flag is L means temp has a right child so shift to right and continue the iteration
            if(flag == 'L') {
                temp = temp.getRight();
            }
            else {
                while(true) {
                    //temp's left is done* and temp has no right child so visit temp
                    System.out.print(temp.getData() + " ");
                    if (isRightChild(temp)) {
                        //temp is right child, so locate it's parent and visit parent
                        while (temp.getlFlag() == 'L') {
                            temp = temp.getLeft();
                        }
                        temp = temp.getLeft();
                    } else {
                        //locate parent of left child and go to beginning
                        while(temp.getrFlag() == 'L') {
                            temp = temp.getRight();
                        }
                        temp = temp.getRight();
                        break;
                    }
                }
            }

        }
        System.out.println();
    }

    private boolean isRightChild(Node node) {
        if(node == root) {
            return false;
        }

        Node temp = root;
        while(true) {
            if(node.getData() < temp.getData()) {
                temp = temp.getLeft();
                if(node == temp) {
                    return false;
                }
            }
            else {
                temp = temp.getRight();
                if(temp == node) {
                    return true;
                }
            }
        }
    }
}









