package com.folautech.graphql.dataloader;

import com.folautech.graphql.entities.address.Address;
import com.folautech.graphql.entities.user.User;
import com.folautech.graphql.entities.user.UserRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Slf4j
@Component
public class UserDataLoaderService {
    @Autowired
    private UserRepository userRepository;

    private Faker faker = new Faker();

    private Random random = new Random();

    public void load(){

        loadAdminUsers();

        Address address = Address.builder()
                .street("123 Halloween St")
                .city("Lehi")
                .state("UT")
                .zipcode("83043")
                .build();

        User user = null;

        for (int i=3;i<=20;i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            user = User.builder()
                    .id(Long.valueOf(""+i))
                    .firstName(firstName)
                    .lastName(lastName)
                    .dob(LocalDate.of(1986,12,03))
                    .email((firstName+lastName).toLowerCase()+"@gmail.com")
                    .phoneNumber(faker.phoneNumber().cellPhone())
                    .address(address)
                    .build();

            address.setUser(user);

            userRepository.saveAndFlush(user);
        }

        log.info("done loading user data!!!");

    }

    private void loadAdminUsers(){
        Address address = Address.builder()
                .street("123 Halloween St")
                .city("Lehi")
                .state("UT")
                .zipcode("83043")
                .build();

        User folau = User.builder()
                .id(1L)
                .firstName("Folau")
                .lastName("Kaveinga")
                .dob(LocalDate.of(1986,12,03))
                .email("folaukaveinga@gmail.com")
                .phoneNumber("3109934731")
                .address(address)
                .build();

        address.setUser(folau);

        userRepository.saveAndFlush(folau);

        User lisa = User.builder()
                .id(2L)
                .firstName("Lisa")
                .lastName("Kaveinga")
                .dob(LocalDate.of(1987,04,12))
                .email("lisakaveinga@gmail.com")
                .phoneNumber("3439934731")
                .address(address)
                .build();

        address.setUser(lisa);

        userRepository.saveAndFlush(lisa);
    }

}
