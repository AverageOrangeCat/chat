package org.chat.application.service.account;

import java.time.LocalDateTime;

import org.chat.infrastructure.entity.AccountEntity;
import org.chat.infrastructure.entity.SessionEntity;
import org.chat.infrastructure.repository.AccountRepository;
import org.chat.infrastructure.repository.SessionRepository;
import org.chat.shared.constant.Constant;
import org.chat.shared.time.TimeService;
import org.chat.shared.uuid.UuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public AccountEntity authenticateByAcessToken(String accessToken) throws Exception {
        SessionEntity sessionEntity = sessionRepository.findSessionEntityByAcessToken(accessToken);

        if (sessionEntity == null) {
            throw new Exception("Session not found - 403");
        }

        if (sessionEntity.getExpirationDate().isBefore(TimeService.nowUtc())) {
            throw new Exception("Session expired - 403");
        }

        return sessionEntity.getAccountEntity();
    }

    public AccountDto getAccountData(String accessToken, String usertag) throws Exception {
        AccountEntity authenticatedAccountEntity = authenticateByAcessToken(accessToken);
        AccountEntity requestedAccountEntity = accountRepository.findAccountEntityByUsertag(usertag);

        return new AccountDto()
            .setUsertag(requestedAccountEntity.getUsertag())
            .setUsername(requestedAccountEntity.getUsername());
    }

    public void updateAccount(String accessToken, AccountUpdateDto accountUpdateDto) throws Exception {
        AccountEntity authenticatedAccountEntity = authenticateByAcessToken(accessToken);

        if (accountUpdateDto.getUsertag() != "") {
            accountRepository.updateAccountEntityUsertag(
                authenticatedAccountEntity.getId(), 
                accountUpdateDto.getUsertag());
        }

        if (accountUpdateDto.getUsername() != "") {
            accountRepository.updateAccountEntityUsername(
                authenticatedAccountEntity.getId(), 
                accountUpdateDto.getUsername());
        }

        if (accountUpdateDto.getPassword() != "") {
            accountRepository.updateAccountEntityPassword(
                authenticatedAccountEntity.getId(), 
                accountUpdateDto.getPassword());
        }
    }

    public void signupAccount(AccountSignupDto accountSignupDto) {
        accountRepository.createAccountEntity(
            accountSignupDto.getUsertag(),
            accountSignupDto.getUsername(),
            accountSignupDto.getPassword());
    }

    public void signoutAccount(String accessToken) throws Exception {
        AccountEntity authenticatedAccountEntity = authenticateByAcessToken(accessToken);
        accountRepository.deleteAccountEntity(authenticatedAccountEntity.getId());
    }

    public SessionDto loginAccount(AccountLoginDto accountLoginDto) {
        AccountEntity authenticatedAccountEntity = accountRepository.findAccountEntityByUsertagAndPassword(
            accountLoginDto.getUsertag(), 
            accountLoginDto.getPassword());

        String accessToken = UuidService.uuid();

        LocalDateTime expirationDate = TimeService.nowUtc()
            .plusDays(Constant.Session.EXPIRATION_DAYS)
            .plusHours(Constant.Session.EXPIRATION_HOURS)
            .plusMinutes(Constant.Session.EXPIRATION_MINUTES)
            .plusSeconds(Constant.Session.EXPIRATION_SECONDS);

        sessionRepository.createSessionEntity(
            authenticatedAccountEntity.getId(), 
            accessToken, 
            expirationDate);

        return new SessionDto()
            .setAccessToken(accessToken)
            .setExpirationDate(expirationDate);
    }

    public void logoutAccount(String accessToken) throws Exception {
        AccountEntity authenticatedAccountEntity = authenticateByAcessToken(accessToken);
        sessionRepository.deleteSessionEntityy(accessToken);
    }
}
