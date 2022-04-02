package ru.sfedu.javaProject.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sfedu.javaProject.model.Pair;
import ru.sfedu.javaProject.model.gender_models.Female;
import ru.sfedu.javaProject.model.gender_models.Male;
import ru.sfedu.javaProject.repositories.FemaleRepository;
import ru.sfedu.javaProject.repositories.MaleRepository;
import ru.sfedu.javaProject.repositories.PairRepository;

import java.util.Set;

@Component
public class BootstrapPair implements CommandLineRunner {
    final PairRepository pairRepository;
    final FemaleRepository femaleRepository;
    final MaleRepository maleRepository;

    public BootstrapPair(PairRepository repository, FemaleRepository femaleRepository, MaleRepository maleRepository) {
        this.pairRepository = repository;
        this.femaleRepository = femaleRepository;
        this.maleRepository = maleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createSimplePair();
    }
    private void createSimplePair(){
        Male alex = Male.builder().name("alex").build();
        Female lenochka = Female.builder().name("Lenochka").build();
        Female lisa = Female.builder().name("Lisa").build();
        Pair pair1  = Pair.builder().male(alex).female(lenochka).build();
        Pair pair2 = Pair.builder().male(alex).female(lisa).build();
        maleRepository.save(alex);
        femaleRepository.saveAll(Set.of(lenochka, lisa));
        pairRepository.saveAll(Set.of(pair1, pair2));
    }

}
