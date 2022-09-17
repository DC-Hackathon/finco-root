package com.bravura.finco.repository;

import com.bravura.finco.entity.QueryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<QueryData, Long> {}
