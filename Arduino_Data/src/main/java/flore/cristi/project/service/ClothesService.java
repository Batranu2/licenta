package flore.cristi.project.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import flore.cristi.project.model.entity.ClothesEntity;
import flore.cristi.project.model.entity.ClothesType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothesService {

    private final GenericRepo repo;
    final String CLOTHES_TABLE = "Clothes";

    public ClothesService(GenericRepo repo) {
        this.repo = repo;
    }

    public void saveCloth(ClothesEntity clothesEntity) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(CLOTHES_TABLE);
        database.child(clothesEntity.getUid()).setValueAsync(clothesEntity);
    }

    public ClothesEntity getClothByUid(String uid) throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(CLOTHES_TABLE);
        return repo.getEntityByUid(uid, database, ClothesEntity.class);
    }

    public List<ClothesEntity> getAllClothes() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(CLOTHES_TABLE);
        return repo.getAllEntities(database, ClothesEntity.class);
    }

    public List<ClothesEntity> getClothesByType(ClothesType clothesType) throws InterruptedException {
        return getAllClothes().stream()
                .filter(clothesEntity -> clothesEntity != null && clothesEntity.getTip_haina() != null)
                .filter(clothesEntity -> clothesEntity.getTip_haina().equals(clothesType))
                .collect(Collectors.toList());
    }

    public void deleteCloth(String uid) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(CLOTHES_TABLE).child(uid);
        database.removeValue(null);
    }
}
