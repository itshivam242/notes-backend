package com.nagarro.notesbackend.repository;

import com.nagarro.notesbackend.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {
    List<Notes> findTop10ByUserIdOrderByDateDesc(@Param("userId") Integer userId);

}
