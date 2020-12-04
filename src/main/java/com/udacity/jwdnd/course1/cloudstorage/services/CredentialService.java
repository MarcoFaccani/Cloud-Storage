package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    public int saveOrUpdateCrendetial(Credential credential, Integer userId) {
        int addedRows = 0;
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);

        if (credential.getCredentialId() == null) addedRows = credentialMapper.save(credential, userId);
        else addedRows = credentialMapper.update(credential);

        return addedRows;
    }

    public List<Credential> getAllCredentialsByUserId(Integer userId) {
        List<Credential> credentials =  credentialMapper.retrieveAllCredentialsByUserId(userId);
        credentials.forEach( c -> c.setDecryptedPassword(encryptionService.decryptValue(c.getPassword(), c.getKey())) );
        return credentials;
    }


    public int deleteCredential(int credentialId) {
        return credentialMapper.deleteCredentialById(credentialId);
    }
}
