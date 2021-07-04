package flore.cristi.project.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import flore.cristi.project.model.entity.ClothesEntity;
import flore.cristi.project.model.entity.UID;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final GenericRepo repo;
    final String UID_TABLE = "UID";

    public UserService(GenericRepo repo) {
        this.repo = repo;
    }

    public UID login(String uid) throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(UID_TABLE);
        return repo.getEntityByUid(uid, database, UID.class);
    }

    public List<UID> getAllUsers() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(UID_TABLE);
        return repo.getAllEntities(database, UID.class);
    }
}
