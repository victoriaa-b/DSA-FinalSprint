package org.keyin.tree;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeService {

    private  final TreeRepository treeRepository;
    // double check the mapper to ensure it works
    private final ObjectMapper objectmapper; // should make converting to json easier

    @Autowired
    public TreeService(TreeRepository treeRepository, ObjectMapper objectmapper){
        this.treeRepository = treeRepository;
         this.objectmapper = objectmapper;
    }

    // save tree records
    public Tree saveTree(String inputNum, TreeNode treeNode) throws Exception {
        String treeJSON = objectmapper.writeValueAsString(treeNode);
        Tree tree = new Tree(inputNum, treeJSON);
        return treeRepository.save(tree);
    }

    // save input numbers
    // make info (node into json - mapper
    // get all of the trees
    // create tree from a list of int
    // BONUS: balanced tree to the user

}
