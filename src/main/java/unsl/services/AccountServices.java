package unsl.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unsl.entities.Account;
import unsl.repository.AccountRespository;

@Service
public class AccountServices {
    @Autowired
    AccountRespository accountRespository;


    public List<Account> getAll(){
        return accountRespository.findAll();
    }

    public Account getAccount (long accountId){
        return accountRespository.findById(accountId).orElse(null);
    }


    public Account findByHolder (Long holder){
        return accountRespository.findByHolder(holder);
    }

    public Account saveAccount (Account account){
        return accountRespository.save(account);
    }

    public Account updateAccountBalance (Account updatedAccount){
        Account account = accountRespository.findById(updatedAccount.getId()).orElse(null);
        if (account == null){
            return null;
        }
        account.setAccount_balance(updatedAccount.getAccount_balance());
        return accountRespository.save(account);
    }

    public Account updateAccountStatus (Account updatedAccount){
        Account account = accountRespository.findById(updatedAccount.getId()).orElse(null);
        if (account == null){
            return null;
        }
        account.setStatus(updatedAccount.getStatus());
        return accountRespository.save(account);
    }

    public Account deleteAccount (Long accountId){
        Account account = accountRespository.findById(accountId).orElse(null);
        if (account == null){
            return null;
        }
        account.setStatus(Account.Status.BAJA);
        return accountRespository.save(account);

    }



}
