package com.agota.tree;

import com.agota.tree.beans.Node;
import com.agota.tree.beans.Tree;
import com.agota.tree.services.LeafService;
import com.agota.tree.services.NodeService;
import com.agota.tree.services.TreeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeApplicationTests {

    @Autowired
    TreeService treeService;

    @Autowired
    NodeService nodeService;

    @Autowired
    LeafService leafService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddTree() {
        Assert.assertEquals(treeService.trees.size(), 0);
        treeService.createTree((long) 1);
        Assert.assertEquals(treeService.trees.size(), 1);

        treeService.trees.remove();
    }

    @Test
    public void testGetTree() {
        Tree tree = treeService.createTree((long) 1);

        Assert.assertEquals(treeService.getTree((long) 1), tree);
        treeService.trees.remove();
    }

    @Test
    public void testAddNode() {
        Tree tree = treeService.createTree((long) 1);

        Long id = nodeService.addNode((long) 1, (long) 1, "my node");

        Assert.assertEquals(treeService.trees.get(0).getNodes().get(0).getId(), id);

        treeService.trees.remove();
    }

    @Test
    public void testDeleteTreeNode() {
        Tree tree = treeService.createTree((long) 1);

        Long id = nodeService.addNode((long) 1, (long) 1, "my node");
        Assert.assertNotNull(nodeService.getTreeNode(treeService.getTree((long) 1), id));

        nodeService.deleteTreeNode((long) 1, id);
        Assert.assertNull(nodeService.getTreeNode(treeService.getTree((long) 1), id));

        treeService.trees.remove();
    }

    @Test
    public void testGetTreeNode() {
        Tree tree = treeService.createTree((long) 1);

        Node node = new Node("node");
        tree.addNode(node);

        Assert.assertEquals(nodeService.getTreeNode(tree, node.getId()), node);

        treeService.trees.remove();
    }

    //TODO fix
    @Test
    public void testAddLeaf() {
        Tree tree = treeService.createTree((long) 1);
        Long nodeId = nodeService.addNode((long) 1, (long) 1, "my node");

        Assert.assertEquals(nodeService.getTreeNode(tree, nodeId).getLeaves().size(), 0);

        Long leafId = leafService.addLeaf(tree.getId(), nodeId, "leaf");
        Assert.assertEquals(nodeService.getTreeNode(tree, nodeId).getLeaves().size(), 1);

        treeService.trees.remove();
    }
}
