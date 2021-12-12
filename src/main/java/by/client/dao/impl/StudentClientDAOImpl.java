package by.client.dao.impl;

import by.client.dao.SocketManager;
import by.client.dao.StudentClientDAO;
import by.client.entity.Student;
import by.client.entity.User;
import by.client.entity.serverCommunicationEnum.Request;
import by.client.entity.serverCommunicationEnum.Response;
import by.client.entity.serverCommunicationEnum.StudentResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentClientDAOImpl implements StudentClientDAO {
    private final SocketManager socketManager = new SocketManager("localhost", 5555);

    @Override
    public boolean edit(Student newValue) {
        StudentResponse response = socketManager
                .sendRequest(newValue, Request.EDIT);
        return (response != null)
                && (response.getResponse() == Response.OK);
    }

    @Override
    public List<Student> getAll() {
        StudentResponse response = socketManager
                .sendRequest(null, Request.GET_ALL);

        if ((response != null)
                && (response.getResponse() == Response.OK)
                && (response.getBody() instanceof ArrayList<?>)) {
            try {
                return (ArrayList<Student>) response.getBody();
            } catch (ClassCastException e) {
                return new ArrayList<>();
            }
        }

        return new ArrayList<>();
    }

    @Override
    public Student get(int id) {
        StudentResponse response = socketManager
                .sendRequest(id, Request.GET);

        if ((response != null)
                && (response.getResponse() == Response.OK)
                && (response.getBody() instanceof Student student)) {
            return student;
        }

        return null;
    }

    @Override
    public boolean create(Student item) {
        StudentResponse response = socketManager
                .sendRequest(item, Request.CREATE);
        return (response != null)
                && (response.getResponse() == Response.OK);
    }

    @Override
    public User register(User user) {
        StudentResponse response = socketManager
                .sendRequest(user, Request.REGISTER);
        if (response.getBody() instanceof User body) {
            return body;
        }

        return null;
    }

    @Override
    public User login(User user) {
        StudentResponse response = socketManager
                .sendRequest(user, Request.LOGIN);
        if (response.getBody() instanceof User body) {
            return body;
        }

        return null;
    }
}
