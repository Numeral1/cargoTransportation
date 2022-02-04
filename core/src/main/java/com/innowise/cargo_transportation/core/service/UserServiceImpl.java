package com.innowise.cargo_transportation.core.service;


import com.innowise.cargo_transportation.core.dto.request.UserParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.UserRequest;
import com.innowise.cargo_transportation.core.dto.response.UserListResponse;
import com.innowise.cargo_transportation.core.dto.response.UserResponse;
import com.innowise.cargo_transportation.core.entity.QUserEntity;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.entity.UserRoleEntity;
import com.innowise.cargo_transportation.core.exception.LoginAlreadyExistException;
import com.innowise.cargo_transportation.core.exception.PassportAlreadyExistException;
import com.innowise.cargo_transportation.core.repository.RoleRepository;
import com.innowise.cargo_transportation.core.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Data
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PersistenceContext
    private final EntityManager entityManager;
    @PersistenceUnit
    private final EntityManagerFactory entityManagerFactory;

    @Transactional
    @Override
    public Long createUser(UserRequest userRequest){
        UserEntity entity = UserRequest.fromUserRequest(userRequest,userRequest.getPassword());
        UserEntity userRepositoryByLogin = userRepository.findByLogin(userRequest.getLogin());
        UserEntity userRepositoryByPassport = userRepository.findByPassportNum(userRequest.getPassportNum());
        if (userRepositoryByLogin != null){
            throw new LoginAlreadyExistException("Login already exists");
        }
        if (userRepositoryByPassport != null){
            throw new PassportAlreadyExistException("PassportNum already exists");
        }
        userRepository.save(entity);
        Set<UserRoleEntity> roleEntityList = userRequest.getUserRoles().stream()
                .map(UserRoleEntity::new)
                .peek(e -> e.setUser(entity))
                .collect(Collectors.toSet());
        roleRepository.saveAll(roleEntityList);
        return entity.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse findUserById(Long id){
        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId == null){
            throw  new NoSuchElementException("User with id: " + id + "not found");
        }
        return new UserResponse(byId.get());
    }

    @Override
    public UserResponse getUserByLogin(String login) {
        UserEntity user = userRepository.findByLogin(login);
        if (user == null) {
            throw new EntityNotFoundException("User with login: " + login + " not found");
        }
        return new UserResponse(user);
    }
    @Override
    public UserResponse getUserByRefreshToken(String token) {
        UserEntity user = userRepository.findByRefreshToken(token);
        if (user == null) {
            return null;
        }
        return new UserResponse(user);
    }



    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest){
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
        UserEntity entity = UserRequest.fromUserRequest(userRequest, encodedPassword);
        entity.setId(id);
        userRepository.save(entity);
    }
    @Override
    public UserListResponse findList(UserParamsRequest params) {
        Pageable pageable = PageRequest.of(params.getPageNumber(), params.getPageSize());

        BooleanBuilder booleanBuilder = buildWhere(params);
        Iterable<UserEntity> all = userRepository.findAll(booleanBuilder, pageable);
        long count = userRepository.count();

        List<UserResponse> list = StreamSupport.stream(all.spliterator(), false)
                .map(UserResponse::new)
                .collect(Collectors.toList());

        return new UserListResponse(list, count);
    }

    private BooleanBuilder buildWhere(UserParamsRequest params) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (params.getName() != null) {
            booleanBuilder.and(QUserEntity.userEntity.name.like("%" + params.getName() + "%"));
        }
        if (params.getSurname() != null) {
            booleanBuilder.and(QUserEntity.userEntity.surname.like(String.join("", "%", params.getSurname(), "%")));
        }
        if (params.getPatronymic() != null){
            booleanBuilder.and(QUserEntity.userEntity.patronymic.like(String.join("", "%", params.getPatronymic(),"%")));
        }
        if (params.getBeforeBornDate() != null){
            booleanBuilder.and(QUserEntity.userEntity.bornDate.before(params.getBeforeBornDate()));
        }
        if (params.getAfterBornDate() != null){
            booleanBuilder.and((QUserEntity.userEntity.bornDate.after(params.getAfterBornDate())));
        }
        if (params.getTown() != null){
            booleanBuilder.and(QUserEntity.userEntity.town.like(String.join("", "%", params.getTown(), "%")));
        }
        if (params.getStreet() !=null){
            booleanBuilder.and(QUserEntity.userEntity.street.like(String.join("", "%", params.getStreet(), "%")));
        }
        if (params.getFlat() != null){
            booleanBuilder.and(QUserEntity.userEntity.flat.like(String.join("", "%", params.getFlat(), "%")));
        }

        return booleanBuilder;
    }

    @Override
    @Transactional
    public void updateUserRefreshToken(Long id, String token) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
        user.setRefreshToken(token);
    }

    private void ifLoginExist(String login){
        UserEntity userRepositoryByLogin = userRepository.findByLogin(login);
        if (userRepositoryByLogin != null){
            throw new LoginAlreadyExistException("Login already exists");
        }
    }
}
