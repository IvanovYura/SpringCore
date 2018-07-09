package com.epam.spring.core;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;
import com.epam.spring.core.repository.CounterRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConsoleApp {

    private static final String WRONG_ARGUMENTS_AMOUNT = "Wrong amount of arguments";

    private App app;

    private Scanner scanner;

    ConsoleApp(Object app) {
        this.app = (App) app;
        scanner = new Scanner(System.in);
    }

    void start() {
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty() || Commands.getByCommand(line) == null) continue;
            switch (Commands.getByCommand(line)) {
                case QUIT:
                    System.exit(0);
                    break;
                case HELP:
                    usage();
                    break;
                case GET_USERS:
                    getUsers();
                    break;
                case GET_EVENTS:
                    getEvents();
                    break;
                case SET_AUDITORIUM:
                    setAuditorium();
                    break;
                case GET_AUDITORIUMS:
                    getAuditoriums();
                    break;
                case BOOK_TICKET:
                    bookTicket();
                    break;
                case GET_TICKETS:
                    getTickets();
                    break;
                case CREATE_USER:
                    createUser();
                    break;
                case REMOVE_USER:
                    deleteUser();
                    break;
                case GET_USER:
                    getUser();
                    break;
                case GET_AUDITORIUM:
                    getAuditorium();
                    break;
                case GET_TICKET_PRICE:
                    getTicketsPrice();
                    break;
                case GET_EVENT:
                    getEvent();
                    break;
                case GET_EVENT_NAME_INVOCATION_AMOUNT:
                    getEventInvocationAmount();
                    break;
                case GET_EVENT_PRICE:
                    getEventPrice();
                    break;
                case GET_EVENT_PRICE_INVOCATION_AMOUNT:
                    getEventPriceInvocationAmount();
                    break;
                case GET_BOOK_TICKET_INVOCATION_AMOUNT:
                    getBookTicketsInvocationAmount();
                    break;
                case GET_DISCOUNT:
                    getDiscount();
                    break;
                case GET_DISCOUNT_GETTING_INVOCATION_AMOUNT:
                    getDiscountGettingInvocationAmount();
                    break;

            }
        }
    }

    private enum Commands {
        QUIT("quit"),
        HELP("help"),
        BOOK_TICKET("book ticket"),
        GET_USERS("get users"),
        GET_EVENTS("get events"),
        GET_AUDITORIUMS("get auditoriums"),
        SET_AUDITORIUM("set auditorium"),
        GET_AUDITORIUM("get auditorium"),
        GET_TICKETS("get tickets"),
        CREATE_USER("create user"),
        REMOVE_USER("delete user"),
        GET_USER("get user"),
        GET_TICKET_PRICE("get ticket price"),
        GET_EVENT("get event"),
        GET_EVENT_PRICE("get event price"),
        GET_DISCOUNT("get discount"),
        GET_EVENT_NAME_INVOCATION_AMOUNT("get event invocation amount"),
        GET_EVENT_PRICE_INVOCATION_AMOUNT("get event price invocation amount"),
        GET_BOOK_TICKET_INVOCATION_AMOUNT("get book tickets invocation amount"),
        GET_DISCOUNT_GETTING_INVOCATION_AMOUNT("get getting discount invocation amount");

        private static final Map<String, Commands> map = new HashMap<>();

        static {
            Arrays.stream(values()).forEach(value -> map.putIfAbsent(value.command, value));
        }

        private String command;

        Commands(String command) {
            this.command = command;
        }

        static Commands getByCommand(String command) {
            return map.get(command);
        }
    }

    private void usage() {
        System.out.println("Available commands:");
        Commands.map.forEach((key, value) -> System.out.println(key));
    }

    private void getEventPrice() {
        while (true) {
            System.out.println("Specify event name");
            String line = scanner.nextLine();
            if (parseLine(line).length != 1) {
                continue;
            }
            double price = app.eventService.getEventPrice(line);
            if (price == 0) {
                System.out.println("There is no price for such event");
                continue;
            }
            System.out.println(price);
            break;
        }
    }

    private void getDiscount() {
        while (true) {
            System.out.println("Specify user email, event name, date time");
            String line = scanner.nextLine();
            String[] args = parseLine(line);
            if (args.length < 3) {
                System.out.println(WRONG_ARGUMENTS_AMOUNT);
                continue;
            }
            User user = app.userService.getUserByEmail(args[0]);
            if (Objects.isNull(user)) {
                System.out.println("There is no such user");
                continue;
            }
            Event event = app.eventService.getByName(args[1]);
            System.out.println(app.discountService.getDiscount(user, event, LocalDate.parse(args[2])));
            break;
        }
    }

    private void getUsers() {
        app.userService.getAll().forEach(System.out::println);
    }

    private void getEvents() {
        app.eventService.getAll().forEach(System.out::println);
    }

    private void getAuditoriums() {
        app.auditoriumService.getAll().forEach(System.out::println);
    }

    private void getEventInvocationAmount() {
        System.out.println(CounterRepository.get("Event"));
    }

    private void getEventPriceInvocationAmount() {
        System.out.println(CounterRepository.get("EventPrice"));
    }

    private void getBookTicketsInvocationAmount() {
        System.out.println(CounterRepository.get("BookTicket"));
    }

    private void getDiscountGettingInvocationAmount() {
        System.out.println(CounterRepository.get("Discount"));
    }

    private void getEvent() {
        while (true) {
            System.out.println("Specify event name");
            String line = scanner.nextLine();
            if (parseLine(line).length != 1) {
                continue;
            }
            Event event = app.eventService.getByName(line);
            if (Objects.isNull(event)) {
                System.out.println("There is no such event");
                continue;
            }
            System.out.println(event.toString());
            break;
        }
    }

    private void getTickets() {
        while (true) {
            System.out.println("Specify event name, date time");
            String line = scanner.nextLine();
            if ("back".equals(line)) {
                break;
            }
            String[] args = parseLine(line);
            if (args.length < 2) {
                System.out.println(WRONG_ARGUMENTS_AMOUNT);
                continue;
            }
            Event event = app.eventService.getByName(args[0]);
            if (Objects.isNull(event)) {
                System.out.println("There is no such event");
            } else {
                app.bookingService.getPurchasedTicketsForEvent(event, LocalDateTime.parse(args[1]))
                        .forEach(System.out::println);
                break;
            }
        }
    }

    private void createUser() {
        while (true) {
            System.out.println("Specify first name, last name and email");
            String[] args = parseLine(scanner.nextLine());
            if (args.length < 3) {
                System.out.println(WRONG_ARGUMENTS_AMOUNT);
                continue;
            }
            User user = new User();
            user.setFirstName(args[0]);
            user.setLastName(args[1]);
            user.setEmail(args[2]);
            System.out.println(app.userService.save(user).toString());
            break;
        }

    }

    private void getUser() {
        while (true) {
            System.out.println("Specify user id to get");
            String line = scanner.nextLine();
            if (line.length() < 1) {
                System.out.println(WRONG_ARGUMENTS_AMOUNT);
            }
            User user = app.userService.getById(Long.parseLong(line));
            if (Objects.isNull(user)) {
                System.out.println("There is no such user with following id: " + line);
                continue;
            }
            break;
        }
    }

    private void deleteUser() {
        while (true) {
            System.out.println("Specify user id to delete");
            String line = scanner.nextLine();
            if (line.length() < 1) {
                System.out.println(WRONG_ARGUMENTS_AMOUNT);
                continue;
            }
            app.userService.remove(Long.parseLong(line));
            break;
        }
    }

    private void bookTicket() {
        while (true) {
            System.out.println("Specify user email, event name, date time and seat number");
            String line = scanner.nextLine();
            String[] args = parseLine(line);
            if (args.length < 4) {
                System.out.println(WRONG_ARGUMENTS_AMOUNT);
                continue;
            }
            User user = app.userService.getUserByEmail(args[0]);
            if (Objects.isNull(user)) {
                System.out.println("There is no such user");
                continue;
            }
            Event event = app.eventService.getByName(args[1]);
            Set<Ticket> tickets = Stream.of(
                    new Ticket(user, event, LocalDateTime.parse(args[2]), Long.parseLong(args[3])))
                    .collect(Collectors.toSet());
            user.setTickets(tickets);
            app.bookingService.bookTickets(tickets);
            break;
        }
    }

    private void setAuditorium() {
        while (true) {
            System.out.println("Specify room name, number of seats and ids [x, y, z] for vip seats");
            String line = scanner.nextLine();
            String[] args = parseLine(line);
            if (args.length < 3) {
                System.out.println("Wrong amount of arguments");
                continue;
            }
            Auditorium auditorium = new Auditorium();
            auditorium.setName(args[0]);
            auditorium.setNumberOfSeats(Long.parseLong(args[1]));
            Set<Long> vipSeats = Stream.of(parseLine(args[2]))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            auditorium.setVipSeats(vipSeats);
            if (!Objects.isNull(app.auditoriumService.save(auditorium))) {
                System.out.println(auditorium.toString());
                System.out.println("You are returned to the main menu");
                break;
            }
        }
    }

    private void getAuditorium() {
        while (true) {
            System.out.println("Specify room name");
            String line = scanner.nextLine();
            Auditorium auditorium = app.auditoriumService.getByName(line);
            if (Objects.nonNull(auditorium)) {
                System.out.println(auditorium.toString());
                break;
            } else {
                System.out.println("There is no such room");
            }
        }
    }

    private void getTicketsPrice() {
        System.out.println("Specify user email, event name, date time and seats [x, y, z]");
        String[] args = parseLine(scanner.nextLine());
        User user = app.userService.getUserByEmail(args[0]);
        Event event = app.eventService.getByName(args[1]);
        LocalDate date = LocalDate.parse(args[2]);
        Set<Long> seats = Stream.of(parseLine(args[3].replaceAll("\\]|\\[", "")))
                .map(Long::parseLong)
                .collect(Collectors.toSet());
        System.out.println(app.bookingService.getTicketsPrice(event, date, user, seats));
    }

    private String[] parseLine(String line) {
        return line.split(" ");
    }
}
