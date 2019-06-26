package com.agota.tree.services;

import com.agota.tree.beans.Leaf;
import com.agota.tree.beans.Node;
import com.agota.tree.beans.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrinterService {

    private TreeService treeService;

    @Autowired
    public PrinterService(TreeService treeService) {
        this.treeService = treeService;
    }

    public Node printTree(Long id) {
        System.out.println();
        System.out.println("Tree number: " + id);

        Tree tree = treeService.getTree(id);

        for (Node node : tree.getNodes()) {
            System.out.println(" | ");

            if (node.getLeaves().size() != 0) {
                printLeaf(node);
            }

            if (node.getNodes().size() != 0) {
                return printNodeNode(node);
            }
        }
        return null;
    }

    private Node printNodeNode(Node parentNode) {

        for (Node node : parentNode.getNodes()) {
            System.out.print("/");

            if (node.getLeaves().size() != 0) {
                printLeaf(node);
            }

            if (node.getNodes().size() != 0) {
                return printNodeNode(node);
            }
        }
        return null;
    }

    private void printLeaf(Node node) {
        for (Leaf leaf : node.getLeaves()) {
            System.out.print("o");
        }
        System.out.println();
    }

}
