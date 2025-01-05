package com.lcaohoanq.demo.domain.user;

public interface IUserService {

    void save(User user);
    User isExist(String userId);

}
