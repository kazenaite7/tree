package com.agota.tree.services;

import com.agota.tree.beans.Tree;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class TreeService {

    public LinkedList<Tree> trees = new LinkedList<>();

    public Tree createTree(Long id) {
        if (getTree(id) == null) {
            Tree tree = new Tree(id);
            trees.add(tree);
            return tree;
        }
        return null;
    }

    public Tree getTree(Long id) {
        for (Tree tree : trees) {
            if (tree.getId().equals(id)) {
                return tree;
            }
        }
        return null;
    }

}
