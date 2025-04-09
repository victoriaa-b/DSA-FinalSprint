package org.keyin.tree.repository;


import org.keyin.tree.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    List<Tree> findAll();
    // double check
}