public class BinaryTree {
    class node{
        Object data;
        node left;
        node right;
        int height;

        node(Object data){
            this.data = data;
            this.left = null;
            this.right = null;
            height = 0;
        }

        boolean isLeaf(){
            return left == null && right == null;
        }
    }

    node root;
    BinaryTree(){
        root = null;
    }

    boolean isFullBinaryTree(node leaf){
        if(leaf == null) {
            return true;
        }

        if(leaf.left == null && leaf.right == null ) {
            return true;
        }

        if((leaf.left!=null) && (leaf.right!=null)){
            return (isFullBinaryTree(leaf.left) && isFullBinaryTree(leaf.right));
        }
        return false;
    }

    boolean isBalancedBinaryTree(node leaf){
        int leftHeight;
        int rightHeight;
        if (leaf == null) {
            return true;
        }

        leftHeight = height(leaf.left);
        rightHeight = height(leaf.right);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalancedBinaryTree(leaf.left) && isBalancedBinaryTree(leaf.right)) {
            return true;
        }
        return false;
    }

    void insert(Object value){
        root = insert(root, Integer.parseInt(value.toString()));
    }

    int height(node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    int getBalance(node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    node rightRotate(node y) {
        node x = y.left;
        node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    node leftRotate(node x) {
        node y = x.right;
        node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    node insert(node root, int key)
    {
        if (root == null)
            return new node(key);

        if (key < Integer.parseInt(root.data.toString()))
            root.left = insert(root.left, key);
        else if (key > Integer.parseInt(root.data.toString()))
            root.right = insert(root.right, key);
        else
            return root;

        root.height = 1 + Math.max(height(root.left),
                height(root.right));

        int balance = getBalance(root);
        if (balance > 1 && key < Integer.parseInt(root.left.data.toString()))
            return rightRotate(root);

        if (balance < -1 && key > Integer.parseInt(root.right.data.toString()))
            return leftRotate(root);

        if (balance > 1 && key > Integer.parseInt(root.left.data.toString())) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && key < Integer.parseInt(root.right.data.toString())) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }
    void delete(Object value){
        root = deleteNode(root, Integer.parseInt(value.toString()));
    }


    node minValueNode(node node) {
        node current = node;

        // loop down to find the leftmost leaf
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    node deleteNode(node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < Integer.parseInt(root.data.toString())) {
            root.left = deleteNode(root.left, key);
        } else if (key > Integer.parseInt(root.data.toString())) {
            root.right = deleteNode(root.right, key);
        } else {
            if ((root.left == null) || (root.right == null)) {
                node temp = root.left != null ?
                        root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;

            } else {
                node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = deleteNode(root.right, Integer.parseInt(temp.data.toString()));
            }
        }

        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left),
                height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void printPreOrder(node leaf){
        if(leaf == null) return;
        System.out.print(leaf.data + " ");
        printPreOrder(leaf.left);
        printPreOrder(leaf.right);
    }

    void printInOrder(node leaf){
        if(leaf == null) return;
        printInOrder(leaf.left);
        System.out.print(leaf.data + " ");
        printInOrder(leaf.right);
    }
    void printPostOrder(node leaf){
        if(leaf == null) return;
        printPostOrder(leaf.left);
        printPostOrder(leaf.right);
        System.out.print(leaf.data + " ");
    }

    boolean search(Object value){
        return false;
    }
    void destroyTree(node leaf){
        if (leaf == null) return;
        // find left
        if (leaf.left != null) {
            destroyTree(leaf.left);
            // process
            leaf.left = null;
        }
        // find right
        if (leaf.right != null) {
            destroyTree(leaf.right);
            // process
            leaf.right = null;
        }
        // set root is null
        if(root.isLeaf()){
            root = null;
        }
    }
}