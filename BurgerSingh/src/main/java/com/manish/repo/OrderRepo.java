package com.manish.repo;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manish.entity.MenuItem;


@Repository
public interface OrderRepo extends JpaRepository<MenuItem, Integer>{

}
