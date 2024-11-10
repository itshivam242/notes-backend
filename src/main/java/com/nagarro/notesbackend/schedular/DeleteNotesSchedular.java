package com.nagarro.notesbackend.schedular;

import com.nagarro.notesbackend.entity.Notes;
import com.nagarro.notesbackend.entity.User;
import com.nagarro.notesbackend.repository.NotesRepository;
import com.nagarro.notesbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeleteNotesSchedular {
    @Autowired
    UserRepository userRepository;

    @Autowired
    NotesRepository noteRepository;

    @Scheduled(cron = "0 0 * * * *")
    public void scheduleTask()
    {
        List<User> users = userRepository.findAll();
        int limit = 10;

        for (User user : users) {
            List<Notes> notes = user.getNotes();
            if (notes.size() > limit) {
                List<Notes> notesToDelete = notes.stream()
                        .sorted((n1, n2) -> n2.getDate().compareTo(n1.getDate()))
                        .skip(limit)
                        .collect(Collectors.toList());

                noteRepository.deleteAllInBatch(notesToDelete);;
            }
        }
        System.out.println("Records Deleted");
    }
}
