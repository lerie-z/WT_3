package by.client.presentation.view;

import by.client.entity.User;
import by.client.service.StudentClientService;

public class AdminView extends PresentationView {
    public AdminView(StudentClientService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        System.out.println("1: Get\n2: Edit\n3: Create\n4: exit");
    }

    @Override
    public PresentationView getInput(String input) {
        return switch (input) {
            case "1" -> new GetSelectView(this.studentService, this.currentUser);
            case "2" -> new EditSelectView(this.studentService, this.currentUser);
            case "3" -> new CreateView(this.studentService, this.currentUser);
            case "4" -> null;
            default -> throw new IllegalArgumentException();
        };
    }
}
