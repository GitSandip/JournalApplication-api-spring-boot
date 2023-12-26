package aurora.JournalApp.service;

import aurora.JournalApp.entity.JournalEntry;
import aurora.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry j){
        journalEntryRepository.save(j);
    }

    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public ResponseEntity<JournalEntry> getEntryById(ObjectId ID) {
        Optional<JournalEntry> optionalEntry = journalEntryRepository.findById(ID);

        if (optionalEntry.isPresent()) {
            JournalEntry entry = optionalEntry.get();
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<JournalEntry> deleteById(ObjectId deleteId){
        Optional<JournalEntry> optionalEntry = journalEntryRepository.findById(deleteId);

        if (optionalEntry.isPresent()) {
            JournalEntry entry = optionalEntry.get();
            journalEntryRepository.deleteById(deleteId);
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
