package org.hypercontract.hypershop.userProfile;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userProfile")
@AllArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public UserProfile get() {
        return userProfileService.get();
    }
}
