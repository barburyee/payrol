package com.kimeli.payrol.reposotory;

import com.kimeli.payrol.model.User;
import com.kimeli.payrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRespository userRespository;
    @Override
    public List<User> getUser() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
