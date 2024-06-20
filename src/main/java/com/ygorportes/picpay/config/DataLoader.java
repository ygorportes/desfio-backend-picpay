package com.ygorportes.picpay.config;

import com.ygorportes.picpay.entity.WalletType;
import com.ygorportes.picpay.repository.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running DataLoader...");
        Arrays.stream(WalletType.Enum.values())
                .forEach(walletTypeEnum -> {
                    WalletType walletType = walletTypeEnum.get();
                    WalletType saved = walletTypeRepository.save(walletType);
                    System.out.println("Saved WalletType: " + saved.getDescription());
                });
    }
}
