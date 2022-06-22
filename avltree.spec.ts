import { Tree } from "./avltree";

describe(`AVL tree testing`, () => {
    it(`should insert values into the tree and return that it contains these values`, () => {
        const tree = new Tree();

        [...Array(4).keys()].forEach(number => tree.insert(number));


        [...Array(4).keys()].forEach(number => expect(tree.contains(number)).toEqual(true));

        expect(tree.contains(4)).toEqual(false);
        expect(tree.contains(-1)).toEqual(false);
    })

    it("should calculate the height of the tree", () => {
        const emptyTree = new Tree();
        const oneElementTree = new Tree();
        const fewElementsTree = new Tree();

        oneElementTree.insert(1);

        fewElementsTree.insert(2);
        fewElementsTree.insert(1);
        fewElementsTree.insert(3);

        expect(emptyTree.height).toEqual(0);
        expect(oneElementTree.height).toEqual(1);
        expect(fewElementsTree.height).toBeGreaterThan(1);
    });

    it("should balance the height of the tree on the ascending insertions", () => {
        const tree = new Tree();

        [...Array(7).keys()].forEach(number => tree.insert(number));

        expect(tree.height).toEqual(3);
    });

    it("should balance the height of the tree on the descending insertions", () => {
        const tree = new Tree();

        [...Array(7).keys()].reverse().forEach(number => tree.insert(number));

        expect(tree.height).toEqual(3);
    });

    it("should balance the height of the tree when LR balancing is required", () => {
        const tree = new Tree();

        [50, 20, 60, 10, 8, 15, 32, 46, 11, 48].forEach(number => tree.insert(number));

        expect(tree.height).toEqual(4);
    })
});