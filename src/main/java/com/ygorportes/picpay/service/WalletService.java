package com.ygorportes.picpay.service;

import com.ygorportes.picpay.controller.dto.CreateWalletDto;
import com.ygorportes.picpay.entity.Wallet;
import com.ygorportes.picpay.exception.WalletDataAlreadyExistsException;
import com.ygorportes.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
        if (walletDb.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
