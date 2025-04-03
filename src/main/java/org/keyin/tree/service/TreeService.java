package org.keyin.tree.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.keyin.tree.model.Tree;
import org.keyin.tree.model.TreeNode;
import org.keyin.tree.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeService {

    private final TreeRepository treeRepository;
    // double check the mapper to ensure it works
    private final ObjectMapper objectmapper; // should make converting to json easier

    @Autowired
    public TreeService(TreeRepository treeRepository, ObjectMapper objectmapper){
        this.treeRepository = treeRepository;
        this.objectmapper = objectmapper;
    }

    // save tree records
    // save input numbers
    public Tree saveTree(String inputNum, TreeNode treeNode) throws Exception {
        String treeJSON = objectmapper.writeValueAsString(treeNode);
        Tree tree = new Tree(inputNum, treeJSON);
        return treeRepository.save(tree);
    }

    // create tree from a list of int
    private  List<Integer> parseNum(String numbers){
        List<Integer> answer = new ArrayList<>();

        if (numbers == null || numbers.trim().isEmpty()){
            return answer;
        }

        String[] elements = numbers.split(",");

        for (String element : elements) {
            try {
                answer.add(Integer.parseInt(element.trim()));
            } catch (NumberFormatException error){
                // maybe add message
            }
        }
        return answer;
    }
    // get all of the trees
    // BONUS: balanced tree to the user

}
