package flore.cristi.project.controller;

import flore.cristi.project.model.entity.ClothesEntity;
import flore.cristi.project.model.entity.ClothesType;
import flore.cristi.project.service.ClothesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ClothesController {

    private final ClothesService clothesService;

    @Autowired
    public ClothesController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    /*@RequestMapping("/weather_api")
    public ResponseEntity<ClothesEntity> getApi(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("https://weather.com/swagger-docs/ui/sun/v2/turboCurrentsonDemand.json", ClothesEntity.class);
    }*/


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/save-clothes")
    public void saveCloth(@RequestBody ClothesEntity clothesEntity) {
        clothesService.saveCloth(clothesEntity);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clothes/{uid}")
    public ClothesEntity getClothes(@PathVariable("uid") String uid) throws InterruptedException {
        return clothesService.getClothByUid(uid);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/clothes-type/{tip}")
    public List<ClothesEntity> getClothesByType(@PathVariable("tip") String type) throws InterruptedException {
        return clothesService.getClothesByType(ClothesType.valueOf(type));
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/clothes")
    public List<ClothesEntity> getAllClothes() throws InterruptedException {
        return clothesService.getAllClothes();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/clothes/remove/{uid}")
    public void removeClothes(@PathVariable("uid") String uid) {
        clothesService.deleteCloth(uid);
    }
}
