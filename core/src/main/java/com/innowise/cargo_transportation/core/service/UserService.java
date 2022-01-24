package com.innowise.cargo_transportation.core.service;

import com.innowise.cargo_transportation.core.dto.request.UserParamsRequest;
import com.innowise.cargo_transportation.core.dto.request.UserRequest;
import com.innowise.cargo_transportation.core.dto.response.UserListResponse;
import com.innowise.cargo_transportation.core.dto.response.UserResponse;
import com.innowise.cargo_transportation.core.entity.QUserEntity;
import com.innowise.cargo_transportation.core.entity.UserEntity;
import com.innowise.cargo_transportation.core.repository.UserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(UserRequest userRequest){
        UserEntity entity = UserRequest.fromUserRequest(userRequest);
        userRepository.save(entity);
        return entity.getId();
    }

    public UserResponse findUserById(Long id){
        Optional<UserEntity> byId = userRepository.findById(id);
        UserEntity user = new UserEntity();
        if (byId.isPresent()) {
            user = byId.get();
        }
        return new UserResponse(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(Long id, UserRequest userRequest){
        UserEntity entity = UserRequest.fromUserRequest(userRequest);
        entity.setId(id);
        userRepository.save(entity);
    }

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
}
