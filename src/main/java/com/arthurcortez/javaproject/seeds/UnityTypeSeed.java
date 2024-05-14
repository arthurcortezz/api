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
            unityType1.setName("Colher(es) de sopa");
            unityTypeRepository.save(unityType1);

            UnityTypeEntity unityType2 = new UnityTypeEntity();
            unityType2.setName("Colher(es) de chá");
            unityTypeRepository.save(unityType2);

            UnityTypeEntity unityType3 = new UnityTypeEntity();
            unityType3.setName("Grama(s)");
            unityTypeRepository.save(unityType3);

            UnityTypeEntity unityType4 = new UnityTypeEntity();
            unityType4.setName("Xícara(s)");
            unityTypeRepository.save(unityType4);

            UnityTypeEntity unityType5 = new UnityTypeEntity();
            unityType5.setName("Pitada(s)");
            unityTypeRepository.save(unityType5);

            UnityTypeEntity unityType6 = new UnityTypeEntity();
            unityType6.setName("Unidade(s)");
            unityTypeRepository.save(unityType6);
        }
    }
}