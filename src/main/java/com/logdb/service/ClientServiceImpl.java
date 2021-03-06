package com.logdb.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.logdb.repository.ClientRepository;
import com.logdb.dto.RoleDto;
import com.logdb.dto.ClientDto;
import com.logdb.mapper.ClientMapper;

@Component
public class ClientServiceImpl implements ClientService {

    protected static final String CLIENT = "CLIENT";

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected ClientMapper clientMapper;

    @Override
    public ClientDto findAllById(long id) {
        return clientMapper.convert(clientRepository.findAllById(id));
    }

    @Override
    public ClientDto findAllByEmail(String email) {
        return clientMapper.convert(clientRepository.findAllByEmail(email));
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        initializeClientData(clientDto);
        return clientMapper.convert(clientRepository.save(clientMapper.convert(clientDto)));
    }

    protected void initializeClientData(ClientDto clientDto) {
        RoleDto roleDto =  new RoleDto();
        roleDto.setRole(CLIENT);
        Set<RoleDto> roleDtos = new HashSet<RoleDto>();
        roleDtos.add(roleDto);
        clientDto.setRoles(roleDtos);
        clientDto.setActive(true);
        clientDto.setPassword(bCryptPasswordEncoder.encode(clientDto.getPassword()));
    }
}
