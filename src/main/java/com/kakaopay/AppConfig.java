package com.kakaopay;

import com.kakaopay.service.FindService;
import com.kakaopay.service.FindServiceImpl;
import com.kakaopay.model.account.AccountRepository;
import com.kakaopay.model.account.MemoryAccountRepository;
import com.kakaopay.model.branch.BranchRepository;
import com.kakaopay.model.branch.MemoryBranchRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kakaopay.model.transaction.MemoryTransactionRepository;
import com.kakaopay.model.transaction.TransactionRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class AppConfig {
    @Bean
    public AccountRepository accountRepository(){
        return new MemoryAccountRepository();
    }

    @Bean
    public BranchRepository branchRepository(){
        return new MemoryBranchRepository();
    }

    @Bean
    public TransactionRepository transactionRepository(){
        return new MemoryTransactionRepository();
    }


    @Bean
    public FindService findService(){
        return new FindServiceImpl(accountRepository(), branchRepository(), transactionRepository());
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
