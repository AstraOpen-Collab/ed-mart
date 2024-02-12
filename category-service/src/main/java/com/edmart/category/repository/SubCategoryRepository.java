package com.edmart.category.repository;

import com.edmart.category.entity.SubCategory;
import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Api(tags = "SubCategory Entity")
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
