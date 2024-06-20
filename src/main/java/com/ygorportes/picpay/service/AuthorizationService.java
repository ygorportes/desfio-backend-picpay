package com.ygorportes.picpay.service;

import com.ygorportes.picpay.client.AuthorizationClient;
import com.ygorportes.picpay.controller.dto.TransferDto;
import com.ygorportes.picpay.entity.Transfer;
import com.ygorportes.picpay.exception.PicPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transfer) {

        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()){
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
