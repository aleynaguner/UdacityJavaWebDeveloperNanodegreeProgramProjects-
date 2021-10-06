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

    public int createCredential(String url, String username, String key, String password, Integer userId) {
        Credential credential = new Credential(0, url, username, key, password, userId );
        return credentialMapper.createCredential(credential);
    }

    public Credential getCredentialById(Integer credentialId) {
        return credentialMapper.getCredentialById(credentialId);
    }

    public List<Credential> getCredentialsForUser(Integer userId) {
        return credentialMapper.getCredentialsForUser(userId);
    }

    public int updateCredential(Integer credentialId, String url, String username, String key, String password, Integer userId) {
        return credentialMapper.updateCredential(credentialId, url, username, key, password, userId);
    }

    public int deleteCredentialById(Integer credentialId) {
        return credentialMapper.deleteCredentialById(credentialId);
    }
}
