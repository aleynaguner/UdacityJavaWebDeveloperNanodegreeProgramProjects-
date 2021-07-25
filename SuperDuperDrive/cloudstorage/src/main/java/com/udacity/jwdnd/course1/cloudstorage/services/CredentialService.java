package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private UserMapper userMapper;

    public CredentialService(CredentialMapper credentialMapper, UserMapper userMapper) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    public void createNewCredential(Credential credential, String username) {
        Integer userId = userMapper.getUserIdByUsername(username);
        credential.setUserId(userId);
        credentialMapper.createNewCredential(credential);
    }

    public Credential getCredentialById(Integer credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }

    public List<Credential> getCredentialsForUser(Integer userId) {
        return credentialMapper.getCredentialsForUser(userId);
    }

    public Credential updateCredential(Credential credential) {
        return credentialMapper.updateCredential(credential);
    }

    public void deleteCredentialById(Integer credentialId) {
        credentialMapper.deleteCredentialById(credentialId);
    }
}
