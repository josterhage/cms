package com.system559.cms.repository;

import com.system559.cms.model.NoteBoard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteBoardRepository extends MongoRepository<NoteBoard, String> {
}
