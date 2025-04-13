package org.keyin.tree.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keyin.tree.model.Tree;
import org.keyin.tree.model.TreeNode;
import org.keyin.tree.repository.TreeRepository;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreeServiceTest {

    @Mock
    private TreeRepository treeRepository;

    private TreeService treeService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        treeService = new TreeService(treeRepository, objectMapper);
    }

    // Test for empty input
    @Test
    void testCreateBalBSTreeEmptyInput() {
        TreeNode result = treeService.createBalBSTree("");
        assertNull(result, "The tree will say null for any empty intput");
    }

    // Test using 3 numbers
    @Test
    void testCreateBalBSTreeThreeNumbers() {
        TreeNode result = treeService.createBalBSTree("1,2,3");
        assertNotNull(result);
        assertEquals(2, result.getValue());
        // Left
        assertNotNull(result.getLeft());
        assertEquals(1, result.getLeft().getValue());
        assertNull(result.getLeft().getLeft());
        assertNull(result.getLeft().getRight());
        // Right
        assertNotNull(result.getRight());
        assertEquals(3, result.getRight().getValue());
        assertNull(result.getRight().getLeft());
        assertNull(result.getRight().getRight());
    }

    // Save created Tree
    @Test
    void testSaveTree() throws Exception {
        TreeNode node = treeService.createBalBSTree("1,2,3");
        Tree mockTree = new Tree("1,2,3", objectMapper.writeValueAsString(node));
        when(treeRepository.save(any(Tree.class))).thenReturn(mockTree);
        Tree result = treeService.saveTree("1,2,3", node);
        assertNotNull(result);
        assertEquals("1,2,3", result.getInputNum());
        assertNotNull(result.getTreeStructure());
    }

}