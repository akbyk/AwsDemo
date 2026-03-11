package com.akbyk.awsdemo.iam;

import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.iam.IamClient;
import software.amazon.awssdk.services.iam.model.*;

import java.util.List;

@RestController
@RequestMapping("/iam")
public class IamController {

    private final IamClient iamClient;

    public IamController(IamClient iamClient) {
        this.iamClient = iamClient;
    }

    // List all IAM users in your account
    @GetMapping("/users")
    public List<String> listUsers() {
        return iamClient.listUsers()
                .users()
                .stream()
                .map(User::userName)
                .toList();
    }

    // List all policies attached to a user
    @GetMapping("/users/{username}/policies")
    public List<String> listUserPolicies(@PathVariable String username) {
        return iamClient.listAttachedUserPolicies(
                        ListAttachedUserPoliciesRequest.builder()
                                .userName(username)
                                .build()
                )
                .attachedPolicies()
                .stream()
                .map(AttachedPolicy::policyName)
                .toList();
    }

    // List all roles
    @GetMapping("/roles")
    public List<String> listRoles() {
        return iamClient.listRoles()
                .roles()
                .stream()
                .map(Role::roleName)
                .toList();
    }
}