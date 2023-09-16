package com.caneroksuz.service;

import com.caneroksuz.dto.request.LoginRequestDto;
import com.caneroksuz.dto.request.RegisterRequestDto;
import com.caneroksuz.dto.response.UserResponseDto;
import com.caneroksuz.mapper.IUserMapper;
import com.caneroksuz.repository.IUserRepository;
import com.caneroksuz.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User register(RegisterRequestDto dto) {
//        User user =User.builder().phone(dto.getPhone()).name(dto.getName()).surName(dto.getSurName())
//                .email(dto.getEmail()).password(dto.getPassword()).build();

        User user = IUserMapper.INSTANCE.toUser(dto);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı");
        }

        return user.get();
    }

    public UserResponseDto login(LoginRequestDto dto) {
        Optional<User> user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (user.isEmpty()){
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
//        return UserResponseDto.builder()
//                .id(user.get().getId())
//                .userType(user.get().getUserType())
//                .email(user.get().getEmail())
//                .name(user.get().getName())
//                .surName(user.get().getSurName())
//                .phone(user.get().getPhone())
//                .build();

        return IUserMapper.INSTANCE.toUserResponseDto(user.get());

    }

    public List<User> findAllByOrderByName(){
        return userRepository.findAllByOrderByName();
    }

    public List<UserResponseDto> findAllByNameContainingIgnoreCase(String value){
        List<User> userList= userRepository.findAllByNameContainingIgnoreCase(value);

        if (userList.isEmpty()){
            throw new RuntimeException("herhangi bir veri bulunamadı");
        }

        //uzun yol == tek tek mapleme...
        //List<UserResponseDto> list = userList.stream().map(x-> IUserMapper.INSTANCE.toUserResponseDto(x)).collect(Collectors.toList());


        return IUserMapper.INSTANCE.toUserResponseDtos(userList);
    }

    public List<User> findAllByEmailContainingIgnoreCase(String value){
        return userRepository.findAllByEmailContainingIgnoreCase(value);
    }

    public List<User> findAllByEmailEndingWith(String value){
        return userRepository.findAllByEmailEndingWith(value);
    }

    public List<User> passwordLongerThan(int value){
        return userRepository.passwordLongerThan(value);
    }

    public List<User> passwordLongerThan2(int value) {
        return userRepository.passwordLongerThan2(value);
    }

    public List<User> passwordLongerThan3(int value) {
        return userRepository.passwordLongerThan3(value);
    }
}
