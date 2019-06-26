package com.agota.tree.services;

import com.agota.tree.beans.Node;
import com.agota.tree.beans.Leaf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeafService {

    private NodeService nodeService;
    private TreeService treeService;

    @Autowired
    public LeafService(NodeService nodeService, TreeService treeService) {
        this.nodeService = nodeService;
        this.treeService = treeService;
    }

    public Long addLeaf(Long treeId, Long nodeId, String leafName) {
        Node node = nodeService.getTreeNode(treeService.getTree(treeId), nodeId);
        Leaf leaf = new Leaf(leafName);
        if (node != null) {
            if (isLeafUnique(node, leaf)) {
                node.addLeaf(leaf);
                return leaf.getId();
            }
        }
        return null;
    }

    public Boolean deleteLeaf(Long treeId, Long nodeId, Long leafId) {
        Node node = nodeService.getTreeNode(treeService.getTree(treeId), nodeId);
        for (Leaf leaf : node.getLeaves()) {
            if (leaf.getId().equals(leafId)) {
                node.removeLeaf(leaf);
                return true;
            }
        }
        return false;
    }

    private Boolean isLeafUnique(Node node, Leaf newLeaf) {
        for (Leaf leaf : node.getLeaves()) {
            if (leaf.getId().equals(newLeaf.getId())) {
                return false;
            }
            if (leaf.getName().equals(newLeaf.getName())) {
                return false;
            }
        }
        return true;
    }

}
