package unsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unsl.entities.Account;
import unsl.entities.ResponseError;
import unsl.services.AccountServices;

import java.util.List;


@RestController
public class AccountController {
    @Autowired
    AccountServices accountServices;

    @GetMapping(value = "/account")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAll() {
        return accountServices.getAll();
    }

    @GetMapping(value = "/account/{accountId}")
    @ResponseBody
    public Object getAccount(@PathVariable("id") Long accountId) {
        Account account = accountServices.getAccount(accountId);
        if (account == null) {
            return new ResponseEntity(new ResponseError(404, String.format("There are no accounts associated with this ID", accountId)), HttpStatus.NOT_FOUND);
        }
        return account;
    }

    @GetMapping(value = "/account/search")
    @ResponseBody
    public Object searchAccount(@RequestParam("holder") Long holder) {
        List<Account> account = accountServices.findByHolder(holder);
        if (account == null) {
            return new ResponseEntity(new ResponseError(405, String.format("There are no accounts associated with this holder", holder)), HttpStatus.NOT_FOUND);
        }
        return account;
    }

    @PostMapping(value = "/account")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Object createUser(@RequestBody Account account) {
        if (account == null) {
            return new ResponseEntity(new ResponseError(404, String.format("Missing Data", account)), HttpStatus.NOT_FOUND);
        }
        return accountServices.saveAccount(account);


    }

    @PutMapping(value = "/account")
    @ResponseBody
    public Object updateUser(@RequestBody Account account) {
        Account res = accountServices.updateAccountBalance(account);
        if (account == null) {
            return new ResponseEntity(new ResponseError(404, String.format("Missing Data", account)), HttpStatus.NOT_FOUND);
        }
        return res;

    }


    @DeleteMapping(value = "/account/{id}")
    @ResponseBody
    public Object deleteUser(@PathVariable Long id) {
        Account res = accountServices.deleteAccount(id);
        if (res == null) {
            return new ResponseEntity(new ResponseError(404, String.format("Missing Data", res)), HttpStatus.NOT_FOUND);
        }
        return res;

    }


}
