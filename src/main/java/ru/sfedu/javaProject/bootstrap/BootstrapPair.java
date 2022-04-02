package ru.sfedu.javaProject.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sfedu.javaProject.model.Female;
import ru.sfedu.javaProject.model.Male;
import ru.sfedu.javaProject.model.Pair;
import ru.sfedu.javaProject.repositories.FemaleRepository;
import ru.sfedu.javaProject.repositories.MaleRepository;
import ru.sfedu.javaProject.repositories.PairRepository;

import java.util.Set;

@Component
public class BootstrapPair implements CommandLineRunner {
    final MaleRepository maleRepository;
    final FemaleRepository femaleRepository;
    final PairRepository pairRepository;

    public BootstrapPair(PairRepository repository, FemaleRepository femaleRepository, MaleRepository maleRepository) {
        this.maleRepository = maleRepository;
        this.femaleRepository = femaleRepository;
        this.pairRepository = repository;
    }

    private void example(){
        Male m1 = Male.builder().name("Andrey").build();
        Male m2 = Male.builder().name("Sergey").build();
        Female f1 = Female.builder().name("Alina").build();
        Female f2 = Female.builder().name("Inna").build();
        Pair p1  = Pair.builder().male(m1).female(f1).build();
        Pair p2 = Pair.builder().male(m2).female(f2).build();
        Pair p3 = Pair.builder().male(m1).female(f2).build();
        maleRepository.saveAll(Set.of(m1,m2));
        femaleRepository.saveAll(Set.of(f1, f2));
        pairRepository.saveAll(Set.of(p1, p2));
    }

    @Override
    public void run(String... args) throws Exception {
        example();
    }


}