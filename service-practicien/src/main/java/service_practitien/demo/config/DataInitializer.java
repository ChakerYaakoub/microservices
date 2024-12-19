package service_practitien.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import service_practitien.demo.models.Praticien;
import service_practitien.demo.repositories.PraticienRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PraticienRepository praticienRepository;

    @Override
    public void run(String... args) {
        // Création de 10 praticiens de test
        Praticien praticien1 = new Praticien();
        praticien1.setNom("Dupont");
        praticien1.setPrenom("Jean");
        praticien1.setSpecialite("Cardiologie");
        praticien1.setTelephone("0612345678");
        praticien1.setEmail("jean.dupont@email.fr");
        praticienRepository.save(praticien1);

        Praticien praticien2 = new Praticien();
        praticien2.setNom("Martin");
        praticien2.setPrenom("Sophie");
        praticien2.setSpecialite("Pédiatrie");
        praticien2.setTelephone("0623456789");
        praticien2.setEmail("sophie.martin@email.fr");
        praticienRepository.save(praticien2);

        Praticien praticien3 = new Praticien();
        praticien3.setNom("Dubois");
        praticien3.setPrenom("Pierre");
        praticien3.setSpecialite("Dermatologie");
        praticien3.setTelephone("0634567890");
        praticien3.setEmail("pierre.dubois@email.fr");
        praticienRepository.save(praticien3);

        Praticien praticien4 = new Praticien();
        praticien4.setNom("Bernard");
        praticien4.setPrenom("Marie");
        praticien4.setSpecialite("Ophtalmologie");
        praticien4.setTelephone("0645678901");
        praticien4.setEmail("marie.bernard@email.fr");
        praticienRepository.save(praticien4);

        Praticien praticien5 = new Praticien();
        praticien5.setNom("Petit");
        praticien5.setPrenom("Thomas");
        praticien5.setSpecialite("Psychiatrie");
        praticien5.setTelephone("0656789012");
        praticien5.setEmail("thomas.petit@email.fr");
        praticienRepository.save(praticien5);

        Praticien praticien6 = new Praticien();
        praticien6.setNom("Robert");
        praticien6.setPrenom("Julie");
        praticien6.setSpecialite("Neurologie");
        praticien6.setTelephone("0667890123");
        praticien6.setEmail("julie.robert@email.fr");
        praticienRepository.save(praticien6);

        Praticien praticien7 = new Praticien();
        praticien7.setNom("Richard");
        praticien7.setPrenom("Antoine");
        praticien7.setSpecialite("Rhumatologie");
        praticien7.setTelephone("0678901234");
        praticien7.setEmail("antoine.richard@email.fr");
        praticienRepository.save(praticien7);

        Praticien praticien8 = new Praticien();
        praticien8.setNom("Moreau");
        praticien8.setPrenom("Claire");
        praticien8.setSpecialite("Gynécologie");
        praticien8.setTelephone("0689012345");
        praticien8.setEmail("claire.moreau@email.fr");
        praticienRepository.save(praticien8);

        Praticien praticien9 = new Praticien();
        praticien9.setNom("Simon");
        praticien9.setPrenom("Lucas");
        praticien9.setSpecialite("ORL");
        praticien9.setTelephone("0690123456");
        praticien9.setEmail("lucas.simon@email.fr");
        praticienRepository.save(praticien9);

        Praticien praticien10 = new Praticien();
        praticien10.setNom("Laurent");
        praticien10.setPrenom("Emma");
        praticien10.setSpecialite("Endocrinologie");
        praticien10.setTelephone("0701234567");
        praticien10.setEmail("emma.laurent@email.fr");
        praticienRepository.save(praticien10);
    }
}