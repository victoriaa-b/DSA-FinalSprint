package org.keyin.tree.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.keyin.tree.model.Tree;
import org.keyin.tree.model.TreeNode;
import org.keyin.tree.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class TreeService {

    private final TreeRepository treeRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TreeService(TreeRepository treeRepository, ObjectMapper objectMapper) {
        this.treeRepository = treeRepository;
        this.objectMapper = objectMapper;
    }

    // save tree records
    public Tree saveTree(String inputNum, TreeNode treeNode) throws Exception {
        String treeJSON = objectMapper.writeValueAsString(treeNode);
        Tree tree = new Tree(inputNum, treeJSON);
        return treeRepository.save(tree);
    }

    // convert tree structure from JSON to TreeNode
    public TreeNode remakeTree(String treeJSON) throws Exception {
        return objectMapper.readValue(treeJSON, TreeNode.class);
    }

    private List<Integer> parseNum(String numbers) {
        List<Integer> answer = new ArrayList<>();

        if (numbers == null || numbers.trim().isEmpty()) {
            return answer;
        }
        String[] elements = numbers.split(",");

        for (String element : elements) {
            try {
                answer.add(Integer.parseInt(element.trim()));
            } catch (NumberFormatException error) {

                System.out.println("Invalid format: " + element.trim() + " - " + error.getMessage());
            }
        }
        return answer;
    }

    // create a Binary Search Tree from a list of numbers
    public TreeNode createBSTree(String numbers) {
        List<Integer> numList = parseNum(numbers);

        if (numList.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(numList.remove(0));

        for (int num : numList) {
            root.insert(num);
        }
        return root;
    }

    // get all of the trees from the database
    public List<Tree> getAllTrees() {
        return treeRepository.findAll();
    }






    // BONUS: create a balanced Binary Search Tree
    public TreeNode createBalBSTree(String numbers) {
        Set<Integer> sortedNumbers = new TreeSet<>(parseNum(numbers));
        if (sortedNumbers.isEmpty()) {
            return null;
        }
        return buildBalBST(new ArrayList<>(sortedNumbers), 0, sortedNumbers.size() - 1);
    }

    // helper method to build a balanced tree
    private TreeNode buildBalBST(List<Integer> sortedNumbers, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(sortedNumbers.get(mid));
        node.setLeft(buildBalBST(sortedNumbers, start, mid - 1));
        node.setRight(buildBalBST(sortedNumbers, mid + 1, end));

        return node;
    }
}

