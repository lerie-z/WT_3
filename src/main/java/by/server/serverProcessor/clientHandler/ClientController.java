package by.server.serverProcessor.clientHandler;

import by.client.entity.Student;
import by.client.entity.User;
import by.client.entity.serverCommunicationEnum.Response;
import by.client.entity.serverCommunicationEnum.StudentRequest;
import by.client.entity.serverCommunicationEnum.StudentResponse;
import by.server.service.StudentServerService;

import java.util.List;

public class ClientController {
    private final StudentServerService service;

    public ClientController(StudentServerService service) {
        this.service = service;
    }

    public StudentResponse create(StudentRequest request) {
        Student student;
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Student body) {
            student = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        if (this.service.create(student)) {
            response.setResponse(Response.OK);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponse edit(StudentRequest request) {
        Student student;
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Student body) {
            student = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        if (this.service.edit(student)) {
            response.setResponse(Response.OK);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponse getAll() {
        List<Student> students = this.service.getAll();
        StudentResponse response = new StudentResponse();
        if (students == null) {
            response.setResponse(Response.ERROR);
            response.setBody(null);
        }
        else {
            response.setResponse(Response.OK);
            response.setBody(students);
        }

        return response;
    }

    public StudentResponse get(StudentRequest request) {
        int id;
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof Integer) {
            id = (int) request.getBody();
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        Student studentToSend = this.service.get(id);
        if (studentToSend != null) {
            response.setResponse(Response.OK);
            response.setBody(studentToSend);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponse register(StudentRequest request) {
        User user;
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof User body) {
            user = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        User userResult = this.service.register(user);
        if (userResult != null) {
            response.setResponse(Response.OK);
            response.setBody(userResult);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponse login(StudentRequest request) {
        User user;
        StudentResponse response = new StudentResponse();
        if (request.getBody() instanceof User body) {
            user = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        User userResult = this.service.login(user);
        if (userResult != null) {
            response.setResponse(Response.OK);
            response.setBody(userResult);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponse notFound() {
        StudentResponse response = new StudentResponse();
        response.setResponse(Response.NOTFOUND);
        return response;
    }
}