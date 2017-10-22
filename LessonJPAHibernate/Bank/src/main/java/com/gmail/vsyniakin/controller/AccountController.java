package com.gmail.vsyniakin.controller;

import com.gmail.vsyniakin.entity.Account;
import com.gmail.vsyniakin.entity.CurrencyType;
import com.gmail.vsyniakin.entity.User;

public class AccountController {

    public static void createAccountNewUser (User user){
        if (user.getAccounts().size() == 0) {
            CurrencyType [] currencyTypes = CurrencyType.values();
            for (int i = 0; i < currencyTypes.length; i++) {
                Account account = new Account(user, currencyTypes[i], 0.0);
                user.addAccount(account);
            }
        }
    }
}
