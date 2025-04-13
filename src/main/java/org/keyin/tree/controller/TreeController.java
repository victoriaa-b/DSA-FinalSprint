package org.keyin.tree.controller;

import org.keyin.tree.model.Tree;
import org.keyin.tree.model.TreeNode;
import org.keyin.tree.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TreeController {

    private final TreeService treeService;

    @Autowired
    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }


    @GetMapping("/enter-numbers")
    public String showEnterNumbersPage() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public ResponseEntity<?> processNumbers(
            @RequestParam("numbers") String numbers,
            @RequestParam(value = "balanced", defaultValue = "false") boolean balanced) {

        try {
            TreeNode treeNode;
            if (balanced) {
                treeNode = treeService.createBalBSTree(numbers);
            } else {
                treeNode = treeService.createBSTree(numbers);
            }

            if (treeNode == null) {
                return ResponseEntity.badRequest().body("No valid numbers provided");
            }


            treeService.saveTree(numbers, treeNode);
            Map<String, Object> response = new HashMap<>();
            response.put("tree", treeNode);
            response.put("inputNumbers", numbers);

            return ResponseEntity.ok(response);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("Error processing numbers: " + error.getMessage());
        }
    }

    @GetMapping("/api/trees")
    @ResponseBody
    public ResponseEntity<List<Tree>> getAllTrees() {
        List<Tree> trees = treeService.getAllTrees();
        return ResponseEntity.ok(trees);
    }

    @GetMapping("/api/trees/{id}")
    @ResponseBody
    public ResponseEntity<?> getTreeById(@PathVariable Long id) {
        try {
            return treeService.getAllTrees().stream()
                    .filter(tree -> tree.getId().equals(id))
                    .findFirst()
                    .map(tree -> {
                        try {
                            TreeNode treeNode = treeService.remakeTree(tree.getTreeStructure());
                            Map<String, Object> response = new HashMap<>();
                            response.put("tree", treeNode);
                            response.put("inputNumbers", tree.getInputNum());

                            return ResponseEntity.ok(response);
                        } catch (Exception error) {
                            return ResponseEntity.badRequest()
                                    .body("Error making tree: " + error.getMessage());
                        }
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("Error finding tree: " + error.getMessage());
        }
    }

    @GetMapping("/previous-trees")
    public String showPreviousTreesPage(Model model) {
        List<Tree> trees = treeService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }
}