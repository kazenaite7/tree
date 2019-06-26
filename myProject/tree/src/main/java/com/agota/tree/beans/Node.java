package com.agota.tree.beans;

import java.util.LinkedList;
import java.util.Random;

public class Node {

    private String name;
    private Long id;
    private LinkedList<Node> nodes;
    private LinkedList<Leaf> leaves;

    public Node(String name) {
        this.name = name;
        this.leaves = new LinkedList<>();
        this.nodes = new LinkedList<>();
        this.id = new Random().nextLong();
        Math.abs(this.id);
    }

    public Long getId() {
        return id;
    }

    public LinkedList<Leaf> getLeaves() {
        return leaves;
    }

    public void addLeaf(Leaf leaf) {
        this.leaves.add(leaf);
    }

    public void removeLeaf(Leaf leaf) {
        this.leaves.remove(leaf);
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void removeNode(Node node) {
        this.nodes.remove(node);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }
}
