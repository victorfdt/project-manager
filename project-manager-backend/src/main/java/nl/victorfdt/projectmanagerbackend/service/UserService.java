package nl.victorfdt.projectmanagerbackend.service;

import nl.victorfdt.projectmanagerbackend.data.entity.User;

public interface UserService {

    User findUserByUserName(String userName);
}
