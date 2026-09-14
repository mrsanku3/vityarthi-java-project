package com.vityarthi;

import com.vityarthi.model.User;
import com.vityarthi.model.Task;
import com.vityarthi.model.Resource;
import com.vityarthi.service.AuthManager;
import com.vityarthi.service.TaskManager;
import com.vityarthi.service.ResourceManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static AuthManager authManager = new AuthManager();
    private static TaskManager taskManager = new TaskManager();
    private static ResourceManager resourceManager = new ResourceManager();
    private static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println(" Welcome to Academic Task & Resource Tool ");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showDashboard();
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Select option: ");

        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1":
                try {
                    System.out.print("Enter User ID: ");
                    String id = sc.nextLine().trim();
                    currentUser = authManager.login(id);
                    System.out.println("Login successful. Welcome " + currentUser.getName() + "!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "2":
                try {
                    System.out.print("Enter User ID: ");
                    String id = sc.nextLine().trim();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Enter Role (Student/Faculty): ");
                    String role = sc.nextLine().trim();

                    authManager.register(id, name, email, role);
                    System.out.println("Registration completed successfully!");
                } catch (Exception e) {
                    System.out.println("Registration failed: " + e.getMessage());
                }
                break;

            case "3":
                System.out.println("Exiting application. Goodbye!");
                System.exit(0);

            default:
                System.out.println("Invalid selection. Try again.");
        }
    }

    private static void showDashboard() {
        System.out.println("\n--- Dashboard (" + currentUser.getName() + " | " + currentUser.getRole() + ") ---");
        System.out.println("1. View My Tasks");
        System.out.println("2. Add New Task");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Browse Resources");
        System.out.println("5. Add Resource");
        System.out.println("6. Logout");
        System.out.print("Choice: ");

        String choice = sc.nextLine().trim();
        switch (choice) {
            case "1":
                List<Task> list = taskManager.getUserTasks(currentUser.getId());
                if (list.isEmpty()) {
                    System.out.println("No tasks found.");
                } else {
                    System.out.println("\nID | Title | Deadline | Status");
                    for (Task t : list) {
                        System.out.println(t.getTaskId() + " | " + t.getTitle() + " | " + t.getDeadline() + " | " + t.getStatus());
                    }
                }
                break;

            case "2":
                try {
                    System.out.print("Task ID: ");
                    String tid = sc.nextLine().trim();
                    System.out.print("Title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Deadline (YYYY-MM-DD): ");
                    String dl = sc.nextLine().trim();

                    taskManager.addTask(tid, currentUser.getId(), title, dl);
                    System.out.println("Task added successfully.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "3":
                System.out.print("Enter Task ID to complete: ");
                String doneId = sc.nextLine().trim();
                if (taskManager.markCompleted(doneId, currentUser.getId())) {
                    System.out.println("Task updated.");
                } else {
                    System.out.println("Task not found or unauthorized.");
                }
                break;

            case "4":
                List<Resource> res = resourceManager.getAll();
                if (res.isEmpty()) {
                    System.out.println("No resources listed yet.");
                } else {
                    System.out.println("\nID | Title | Category | Link");
                    for (Resource r : res) {
                        System.out.println(r.getResId() + " | " + r.getTitle() + " | " + r.getCategory() + " | " + r.getLink());
                    }
                }
                break;

            case "5":
                try {
                    System.out.print("Resource ID: ");
                    String rid = sc.nextLine().trim();
                    System.out.print("Title: ");
                    String rtitle = sc.nextLine().trim();
                    System.out.print("Category: ");
                    String cat = sc.nextLine().trim();
                    System.out.print("Link/URL: ");
                    String link = sc.nextLine().trim();

                    resourceManager.addResource(rid, rtitle, cat, link);
                    System.out.println("Resource added.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case "6":
                currentUser = null;
                System.out.println("Logged out.");
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}