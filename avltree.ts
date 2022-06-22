export class Tree {
    private root?: TreeNode;

    get height() {
        return this.root?.height || 0;
    };

    insert = (value: number) => {
        if (this.root)
            return this.root.insert(value);
        this.root = new TreeNode(value);
    }

    contains = (value: number) => !!(this.root?.findNode(value)) || false; 
}

class TreeNode {
    private value: number;
    private left?: TreeNode;
    private right?: TreeNode;

    constructor(value: number) {
        this.value = value;
    }

    get height() {
        return Math.max(this.left?.height || 0, this.right?.height || 0) + 1;
    }

    get balanceFactor() {
        return (this.left?.height || 0) - (this.right?.height || 0);
    }

    processLeafs = (func: Function, value: number) => (value > this.value) ?
        func("right", value) :
        func("left", value);

    insertIntoLeaf = (leaf: "right" | "left", value: number) => {
        if (this[leaf])
            return this[leaf].insert(value);
        this[leaf] = new TreeNode(value);
    }

    insert = (value: number) => {
        this.processLeafs(this.insertIntoLeaf, value);
        if (this.balanceFactor < -1) {
            this.rotateLeft();
        }
        else if (this.balanceFactor > 1) {
            this.rotateRight();
        }
    }

    findInLeaf = (leaf: "right" | "left", value: number) =>
        this[leaf]?.findNode(value) || undefined;

    findNode = (value: number) => (this.value === value) ?
        this : this.processLeafs(this.findInLeaf, value);


    rotateLeft = () => {
        let tmpNode = new TreeNode(this.value);
        tmpNode.left = this.left;
        tmpNode.right = this.right?.left || undefined;

        this.value = this.right.value;
        this.right = this.right.right;
        this.left = tmpNode;
    };

    rotateRight = () => {
        let tmpNode = new TreeNode(this.value);

        tmpNode.right = this.right;
        tmpNode.left = this.left?.right || undefined;

        this.value = this.left.value;
        this.left = this.left.left;
        this.right = tmpNode;
    };
}
