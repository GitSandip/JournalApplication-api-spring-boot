package aurora.JournalApp.controller;

import aurora.JournalApp.entity.JournalEntry;
import aurora.JournalApp.repository.JournalEntryRepository;
import aurora.JournalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService entry;
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @GetMapping  ("/get-all-entry")
    public List<JournalEntry> getAll(){
        System.out.println(entry.getAllEntry());
        return entry.getAllEntry();
    }

    @PostMapping ("add-entry")
    public boolean createEntry(@RequestBody JournalEntry myEntry){

        myEntry.setDate(LocalDateTime.now());
        entry.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId){
        System.out.println("id get...");
        return entry.getEntryById(myId);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<JournalEntry> deleteById(@PathVariable ObjectId myId){

        System.out.println("Record deleted id: "+myId);
        return entry.deleteById(myId);
    }

    @PutMapping("update/id/{myId}")
    public ResponseEntity<JournalEntry> update(@PathVariable ObjectId myId, @RequestBody JournalEntry newObj) {
        Optional<JournalEntry> optionalOld = journalEntryRepository.findById(myId);

        if (optionalOld.isPresent()) {
            JournalEntry old = optionalOld.get();
            old.setTitle(newObj.getTitle() != null && !newObj.getTitle().equals("") ? newObj.getTitle() : old.getTitle());
            old.setContent(newObj.getContent() != null && !newObj.getContent().equals("") ? newObj.getContent() : old.getContent());
            journalEntryRepository.save(old);
            return ResponseEntity.ok(old);
        } else {
            System.out.println("There is No any Entry with id: " + myId);
            return ResponseEntity.notFound().build(); // or handle it in another way, e.g., return a specific error response
        }
    }


}
