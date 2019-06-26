package com.agota.tree.controllers;

import com.agota.tree.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NodeController {

    private NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/node/{treeId}/{parentId}")
    public Long createNode(@PathVariable("treeId") Long treeId, @PathVariable("parentId") Long parentId, @RequestBody String name) {
        return nodeService.addNode(treeId, parentId, name);
    }

    @PutMapping("/node/{parentId}/{nodeId}")
    public Boolean deleteNode(@PathVariable("parentId") Long parentId, @PathVariable("nodeId") Long nodeId) {
        return nodeService.deleteTreeNode(parentId, nodeId);
    }

}