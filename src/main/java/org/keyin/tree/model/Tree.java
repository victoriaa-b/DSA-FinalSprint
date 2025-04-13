package org.keyin.tree.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@Table(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNum;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String treeStructure;

    // constructors
    public Tree() {
    }

    public Tree(String inputNum, String treeStructure) {
        this.inputNum = inputNum;
        this.treeStructure = treeStructure;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public String getInputNum() {
        return inputNum;
    }

    public String getTreeStructure() {
        return treeStructure;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setInputNum(String inputNum) {
        this.inputNum = inputNum;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }
}