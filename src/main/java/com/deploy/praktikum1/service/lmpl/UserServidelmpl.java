package com.deploy.praktikum1.service.lmpl;

import com.deploy.praktikum1.mapper.UserMapper;
import com.deploy.praktikum1.model.dto.UserAddRequest;
import com.deploy.praktikum1.model.dto.UserDto;
import com.deploy.praktikum1.model.entity.User;
import com.deploy.praktikum1.repository.UserRepository;
import com.deploy.praktikum1.service.UserService;
import com.deploy.praktikum1.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public UserDto AddUser(UserAddRequest request) {
        validationUtil.validate(request);

        User saveUser = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .age(request.getAge())
                .build();

        userRepository.save(saveUser);

        UserDto userDto = UserMapper.MAPPER.toUserDtoData(saveUser);

        return userDto;
    }
}
@Override
public List<UserDto> getAllUser() {
    List<User> users = userRepository.findAll();
    List<UserDto> userDto = new ArrayList<>();
    for (User user : users) {
        userDto.add(UserMapper.MAPPER.toUserDtoData(user));
    }
    return userDto;
}

@Override
public UserDto getUserById(String id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    return UserMapper.MAPPER.toUserDtoData(user);
}

@Override
public UserDto UpdateUser(String id, UserAddRequest request) {
    validationUtil.validate(request);

    User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    User user = User.builder()
            .id(existingUser.getId())
            .name(request.getName())
            .age(request.getAge())
            .build();

    userRepository.save(user);

    return UserMapper.MAPPER.toUserDtoData(user);
}

@Override
public void DeleteUser(String id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    userRepository.delete(user);
}
}
