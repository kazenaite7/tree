package com.agota.tree.beans;

import java.util.LinkedList;

public class Tree {
    private Long id;
    LinkedList<Node> nodes;

    public Tree(Long id) {
        this.id = id;
        this.nodes = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void removeNode(Node node) {
        this.nodes.remove(node);
    }
}
