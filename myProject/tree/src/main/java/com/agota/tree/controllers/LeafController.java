package com.agota.tree.controllers;

import com.agota.tree.services.LeafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeafController {

    private LeafService leafService;

    @Autowired
    public LeafController(LeafService leafService) {
        this.leafService = leafService;
    }

    @PostMapping("/leaf/{treeId}/{nodeId}")
    public Long createLeaf(@PathVariable("treeId") Long treeId, @PathVariable("nodeId") Long nodeId, @RequestBody String name) {
        return leafService.addLeaf(treeId, nodeId, name);
    }

    @PutMapping("/leaf/{treeId}/{nodeId}/{leafId}")
    public Boolean deleteLeaf(@PathVariable("treeId") Long treeId, @PathVariable("nodeId") Long nodeId, @PathVariable("leafId") Long leafId) {
        return leafService.deleteLeaf(treeId, nodeId, leafId);
    }
}
