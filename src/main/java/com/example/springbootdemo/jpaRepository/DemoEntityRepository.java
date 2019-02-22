package com.example.springbootdemo.jpaRepository;

import com.example.springbootdemo.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DemoEntityRepository extends JpaRepository<DemoEntity, Long> {

    Optional<DemoEntity> findById(Long aLong);

    DemoEntity findByName(String name);

    DemoEntity findByNameAndAge(String name, String age);

    List<DemoEntity> findAll();
}
