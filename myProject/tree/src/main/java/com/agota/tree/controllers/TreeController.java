package com.agota.tree.controllers;

import com.agota.tree.beans.Tree;
import com.agota.tree.services.PrinterService;
import com.agota.tree.services.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TreeController {

    private TreeService treeService;
    private PrinterService printerService;

    @Autowired
    public TreeController(TreeService treeService, PrinterService printerService) {
        this.treeService = treeService;
        this.printerService = printerService;
    }

    @PostMapping("/tree/{id}")
    public Long createTree(@PathVariable("id") Long id) {
        Tree tree = treeService.createTree(id);
        return tree.getId();
    }

    @GetMapping("/tree/{id}")
    public Tree findTree(@PathVariable("id") Long id) {
        Tree tree = treeService.getTree(id);
        if (tree != null) {
            printerService.printTree(id);
            return tree;
        }
        return null;
    }

}
