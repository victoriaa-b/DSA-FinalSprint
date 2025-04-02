package org.keyin.tree;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trees")
@Getter // use lombok to do most of the getters and setters
@Setter // so cool
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

}