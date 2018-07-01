package com.epam.spring.core;

import com.epam.spring.core.domain.User;

import java.util.Scanner;

class ConsoleApp {

    private App app;

    private Scanner scanner;

    ConsoleApp(App app) {
        this.app = app;
        scanner = new Scanner(System.in);
    }

    void start() {
        while (true) {
            String line = scanner.nextLine();
            switch (line) {
                case "quit":
                    System.exit(0);
                    break;
                case "help":
                    usage();
                    break;
                case "get users":
                    getUsers();
                    break;
            }
        }
    }

    private void usage() {

    }

    private void getUsers() {
        app.userService.getAll().forEach(user -> System.out.println(user.toString()));
    }
}
