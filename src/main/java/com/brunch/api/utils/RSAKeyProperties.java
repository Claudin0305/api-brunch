package com.brunch.api.utils;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class RSAKeyProperties {
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;

    public RSAKeyProperties(){
        KeyPair pair = KeyGenerateUtility.generateRsaKey();
        this.rsaPrivateKey = (RSAPrivateKey) pair.getPrivate();
        rsaPublicKey = (RSAPublicKey) pair.getPublic();
    }

    public RSAPublicKey getRsaPublicKey() {
        return rsaPublicKey;
    }

    public void setRsaPublicKey(RSAPublicKey rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
    }

    public RSAPrivateKey getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(RSAPrivateKey rsaPrivateKey) {
        this.rsaPrivateKey = rsaPrivateKey;
    }
}
