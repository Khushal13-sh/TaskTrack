package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {

	int findById(int id);
}
