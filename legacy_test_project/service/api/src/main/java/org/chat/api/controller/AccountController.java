package org.chat.api.controller;

import org.chat.api.utils.CookieBuilder;
import org.chat.application.service.account.AccountDto;
import org.chat.application.service.account.AccountLoginDto;
import org.chat.application.service.account.AccountService;
import org.chat.application.service.account.AccountSignupDto;
import org.chat.application.service.account.AccountUpdateDto;
import org.chat.application.service.account.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{usertag}")
    public AccountDto getAccountData(
        @CookieValue("accessToken") String accessToken,
        @PathVariable("usertag") String usertag) throws Exception {
        return accountService.getAccountData(accessToken, usertag);
    }

    @PutMapping("/update")
    public void updateAccount(
        @CookieValue("accessToken") String accessToken,
        @RequestBody AccountUpdateDto accountUpdateDto) throws Exception {
        accountService.updateAccount(accessToken, accountUpdateDto);
    }

    @PostMapping("/signup")
    public void signupAccount(@RequestBody AccountSignupDto accountSignupDto) {
        accountService.signupAccount(accountSignupDto);
    }

    @DeleteMapping("/signout")
    public void signoutAccount(@CookieValue("accessToken") String accessToken) throws Exception {
        accountService.signoutAccount(accessToken);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginAccount(@RequestBody AccountLoginDto accountLoginDto) {
        SessionDto sessionDto = accountService.loginAccount(accountLoginDto);

        CookieBuilder cookieBuilder = new CookieBuilder()
            .setName("accessToken")
            .setValue(sessionDto.getAccessToken())
            .setExpirationDate(sessionDto.getExpirationDate());

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookieBuilder.build())
            .build();
    }

    @DeleteMapping("/logout")
    public void logoutAccount(@CookieValue("accessToken") String accessToken) throws Exception {
        accountService.logoutAccount(accessToken);
    }

}
