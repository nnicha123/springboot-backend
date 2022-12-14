package com.users.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.springbootbackend.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}
