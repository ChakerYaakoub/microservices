package service_patient.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import service_patient.demo.models.Patient;
import service_patient.demo.repositories.PatientRepository;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(String... args) {
        // Création de 10 patients de test
        Patient patient1 = new Patient();
        patient1.setNom("Dubois");
        patient1.setPrenom("Marie");
        patient1.setDateNaissance(LocalDate.of(1985, 5, 15));
        patient1.setNumeroSecu("285057544114147");
        patient1.setTelephone("0645789632");
        patient1.setEmail("marie.dubois@email.fr");
        patient1.setAdresse("23 rue des Lilas");
        patient1.setCodePostal("75011");
        patient1.setVille("Paris");
        patientRepository.save(patient1);

        Patient patient2 = new Patient();
        patient2.setNom("Martin");
        patient2.setPrenom("Thomas");
        patient2.setDateNaissance(LocalDate.of(1990, 8, 22));
        patient2.setNumeroSecu("190087544114789");
        patient2.setTelephone("0789632541");
        patient2.setEmail("thomas.martin@email.fr");
        patient2.setAdresse("45 avenue Victor Hugo");
        patient2.setCodePostal("69003");
        patient2.setVille("Lyon");
        patientRepository.save(patient2);

        Patient patient3 = new Patient();
        patient3.setNom("Bernard");
        patient3.setPrenom("Sophie");
        patient3.setDateNaissance(LocalDate.of(1978, 3, 10));
        patient3.setNumeroSecu("278037544114123");
        patient3.setTelephone("0678451236");
        patient3.setEmail("sophie.bernard@email.fr");
        patient3.setAdresse("12 rue de la République");
        patient3.setCodePostal("13001");
        patient3.setVille("Marseille");
        patientRepository.save(patient3);

        Patient patient4 = new Patient();
        patient4.setNom("Petit");
        patient4.setPrenom("Lucas");
        patient4.setDateNaissance(LocalDate.of(1995, 11, 30));
        patient4.setNumeroSecu("195117544114456");
        patient4.setTelephone("0712345678");
        patient4.setEmail("lucas.petit@email.fr");
        patient4.setAdresse("8 rue du Commerce");
        patient4.setCodePostal("44000");
        patient4.setVille("Nantes");
        patientRepository.save(patient4);

        Patient patient5 = new Patient();
        patient5.setNom("Robert");
        patient5.setPrenom("Emma");
        patient5.setDateNaissance(LocalDate.of(1988, 7, 25));
        patient5.setNumeroSecu("288077544114789");
        patient5.setTelephone("0623456789");
        patient5.setEmail("emma.robert@email.fr");
        patient5.setAdresse("34 boulevard Gambetta");
        patient5.setCodePostal("33000");
        patient5.setVille("Bordeaux");
        patientRepository.save(patient5);

        Patient patient6 = new Patient();
        patient6.setNom("Moreau");
        patient6.setPrenom("Antoine");
        patient6.setDateNaissance(LocalDate.of(1982, 4, 18));
        patient6.setNumeroSecu("182047544114159");
        patient6.setTelephone("0734567890");
        patient6.setEmail("antoine.moreau@email.fr");
        patient6.setAdresse("56 rue Pasteur");
        patient6.setCodePostal("59000");
        patient6.setVille("Lille");
        patientRepository.save(patient6);

        Patient patient7 = new Patient();
        patient7.setNom("Simon");
        patient7.setPrenom("Julie");
        patient7.setDateNaissance(LocalDate.of(1992, 9, 5));
        patient7.setNumeroSecu("292097544114753");
        patient7.setTelephone("0645123789");
        patient7.setEmail("julie.simon@email.fr");
        patient7.setAdresse("17 avenue Jean Jaurès");
        patient7.setCodePostal("31000");
        patient7.setVille("Toulouse");
        patientRepository.save(patient7);

        Patient patient8 = new Patient();
        patient8.setNom("Laurent");
        patient8.setPrenom("Nicolas");
        patient8.setDateNaissance(LocalDate.of(1987, 12, 12));
        patient8.setNumeroSecu("187127544114852");
        patient8.setTelephone("0789456123");
        patient8.setEmail("nicolas.laurent@email.fr");
        patient8.setAdresse("89 rue de la Paix");
        patient8.setCodePostal("67000");
        patient8.setVille("Strasbourg");
        patientRepository.save(patient8);

        Patient patient9 = new Patient();
        patient9.setNom("Michel");
        patient9.setPrenom("Claire");
        patient9.setDateNaissance(LocalDate.of(1993, 6, 28));
        patient9.setNumeroSecu("293067544114951");
        patient9.setTelephone("0678912345");
        patient9.setEmail("claire.michel@email.fr");
        patient9.setAdresse("23 rue des Carmes");
        patient9.setCodePostal("35000");
        patient9.setVille("Rennes");
        patientRepository.save(patient9);

        Patient patient10 = new Patient();
        patient10.setNom("Leroy");
        patient10.setPrenom("Pierre");
        patient10.setDateNaissance(LocalDate.of(1980, 2, 15));
        patient10.setNumeroSecu("180027544114357");
        patient10.setTelephone("0612345678");
        patient10.setEmail("pierre.leroy@email.fr");
        patient10.setAdresse("67 avenue de la Libération");
        patient10.setCodePostal("21000");
        patient10.setVille("Dijon");
        patientRepository.save(patient10);
    }
}