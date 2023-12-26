package aurora.JournalApp.repository;

import aurora.JournalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}
