package com.example.demo130.Repository;



import com.example.demo130.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
