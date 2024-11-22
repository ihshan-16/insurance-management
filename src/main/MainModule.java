package main;

import dao.PolicyServiceImpl;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        PolicyServiceImpl policyService = new PolicyServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nInsurance Management System");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Policy Details:");
                    System.out.print("Policy ID: ");
                    int policyId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Policy Name: ");
                    String policyName = scanner.nextLine();
                    System.out.print("Coverage Amount: ");
                    double coverageAmount = scanner.nextDouble();
                    System.out.print("Premium: ");
                    double premium = scanner.nextDouble();

                    Policy newPolicy = new Policy(policyId, policyName, coverageAmount, premium);
                    if (policyService.createPolicy(newPolicy)) {
                        System.out.println("Policy created successfully!");
                    } else {
                        System.out.println("Failed to create policy.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Policy ID: ");
                    int searchId = scanner.nextInt();
                    try {
                        Policy policy = policyService.getPolicy(searchId);
                        if (policy == null) {
                            throw new PolicyNotFoundException("Policy with ID " + searchId + " not found.");
                        }
                        System.out.println("Policy Details: " + policy);
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    List<Policy> policies = policyService.getAllPolicies();
                    System.out.println("All Policies:");
                    policies.forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("Enter Updated Policy Details:");
                    System.out.print("Policy ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Policy Name: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Coverage Amount: ");
                    double updateCoverage = scanner.nextDouble();
                    System.out.print("Premium: ");
                    double updatePremium = scanner.nextDouble();

                    Policy updatePolicy = new Policy(updateId, updateName, updateCoverage, updatePremium);
                    if (policyService.updatePolicy(updatePolicy)) {
                        System.out.println("Policy updated successfully!");
                    } else {
                        System.out.println("Failed to update policy.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Policy ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    if (policyService.deletePolicy(deleteId)) {
                        System.out.println("Policy deleted successfully!");
                    } else {
                        System.out.println("Failed to delete policy.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
