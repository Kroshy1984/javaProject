package ru.sfedu.javaProject.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sfedu.javaProject.model.Gender;
import ru.sfedu.javaProject.model.Pair;
import ru.sfedu.javaProject.model.SUser;
import ru.sfedu.javaProject.repositories.PairRepository;
import ru.sfedu.javaProject.repositories.UserRepository;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class BootstrapPair implements CommandLineRunner {
    final PairRepository pairRepository;
    final UserRepository userRepository;

    public BootstrapPair(PairRepository pairRepository, UserRepository userRepository) {
        this.pairRepository = pairRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createRandomSUsers();
        createRandomPairs();
    }

    private void createRandomSUsers() {
        for (int i = 0; i < 10; i++) {
            SUser sUser = SUser.builder().name("user" + i).gender(Gender.getRandomGender()).build();
            userRepository.save(sUser);
        }
    }
    private void createRandomPairs(){
        Random random = new Random();
        random.ints(10, 1, 10).forEach(i -> {
            SUser sUser1 = userRepository.findById((long) i).get();
            int nextInt = random.nextInt(10) + 1;
            if(nextInt != i){
                SUser sUser2 = userRepository.findById((long) nextInt).get();
                Pair pair = Pair.builder().users(new HashSet<>(Set.of(sUser1, sUser2))).build();
                sUser1.getPairs().add(pair);
                sUser2.getPairs().add(pair);
                userRepository.save(sUser1);
                userRepository.save(sUser2);
                pairRepository.save(pair);
            }
        } );
    }


}
