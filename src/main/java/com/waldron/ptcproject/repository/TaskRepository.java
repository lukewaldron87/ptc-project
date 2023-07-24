package com.waldron.ptcproject.repository;

import com.waldron.ptcproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
