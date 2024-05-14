package com.arthurcortez.javaproject.seeds;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.arthurcortez.javaproject.entity.UnityTypeEntity;
import com.arthurcortez.javaproject.repository.UnityTypeRepository;

@Component
public class UnityTypeSeed implements CommandLineRunner {

    private final UnityTypeRepository unityTypeRepository;

    public UnityTypeSeed(UnityTypeRepository unityTypeRepository) {
        this.unityTypeRepository = unityTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (unityTypeRepository.count() == 0) {
            UnityTypeEntity unityType1 = new UnityTypeEntity();
            unityType1.setName("Frutos do mar");
            unityTypeRepository.save(unityType1);

            UnityTypeEntity unityType2 = new UnityTypeEntity();
            unityType2.setName("Massas");
            unityTypeRepository.save(unityType2);

            UnityTypeEntity unityType3 = new UnityTypeEntity();
            unityType3.setName("Bolos");
            unityTypeRepository.save(unityType3);
        }
    }
}