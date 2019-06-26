package com.agota.tree.services;

import com.agota.tree.beans.Node;
import com.agota.tree.beans.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService {

    private TreeService treeService;

    @Autowired
    public NodeService(TreeService treeService) {
        this.treeService = treeService;
    }

    public Long addNode(Long treeId, Long parentId, String nodeName) {
        Node node = new Node(nodeName);

        Tree treeParent = treeService.getTree(parentId);
        if (treeParent != null) {
            if (isNodeUnique(node, treeParent)) {
                treeParent.addNode(node);
                return node.getId();
            }
        }

        Node parentNode = getTreeNode(treeService.getTree(treeId), parentId);
        if (parentNode != null) {
            if (isNodeUnique(node, treeService.getTree(treeId))) {
                parentNode.addNode(node);
                return node.getId();
            }
        }

        return null;
    }

    public Node createNode(String nodeNam) {
        return new Node(nodeNam);
    }

    public Boolean deleteTreeNode(Long treeId, Long nodeId) {
        Tree tree = treeService.getTree(treeId);
        for (Node node : tree.getNodes()) {
            if (node.getId().equals(nodeId)) {
                tree.removeNode(node);
                return true;
            }
            if (node.getNodes().size() != 0) {
                if (deleteNodeNode(node, nodeId)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean deleteNodeNode(Node parentNode, Long nodeId) {
        for (Node node : parentNode.getNodes()) {
            if (node.getId().equals(nodeId)) {
                parentNode.removeNode(node);
                return true;
            }
            if (node.getNodes().size() != 0) {
                if (deleteNodeNode(node, nodeId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Node getTreeNode(Tree tree, Long nodeId) {
        for (Node node : tree.getNodes()) {
            if (node.getId().equals(nodeId)) {
                return node;
            }
            if (node.getNodes().size() != 0) {
                return getChildNode(node, nodeId);
            }
        }
        return null;
    }

    private Node getChildNode(Node parentNode, Long nodeId) {
        for (Node node : parentNode.getNodes()) {
            if (node.getId().equals(nodeId)) {
                return node;
            }
            if (node.getNodes().size() != 0) {
                return getChildNode(node, nodeId);
            }
        }
        return null;
    }

    private Boolean isNodeUnique(Node newNode, Tree tree) {
        for (Node node : tree.getNodes()) {
            if (node.getId().equals(newNode.getId())) {
                return false;
            }
            if (node.getName().equals(newNode.getName())) {
                return false;
            }
            if (node.getNodes().size() != 0) {
                if (!isNodeUniqueInNode(newNode, node)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Boolean isNodeUniqueInNode(Node newNode, Node parentNode) {
        for (Node node : parentNode.getNodes()) {
            if (node.getId().equals(newNode.getId())) {
                return false;
            }
            if (node.getName().equals(newNode.getName())) {
                return false;
            }
            if (node.getNodes().size() != 0) {
                if (!isNodeUniqueInNode(newNode, node)) {
                    return false;
                }
            }
        }
        return true;
    }
}
