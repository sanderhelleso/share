package com.semanta.share.seeder;

import com.semanta.share.repository.ShareInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private ShareInfoRepository shareInfoRepo;

    @Override
    public void run(String... args) throws Exception {
        // shareInfoRepo.deleteAll();
    }
}