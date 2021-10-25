package com.test.kakaopay;

import com.test.kakaopay.model.account.AccountRepository;
import com.test.kakaopay.model.account.MemoryAccountRepository;
import com.test.kakaopay.model.branch.BranchRepository;
import com.test.kakaopay.model.branch.MemoryBranchRepository;
import com.test.kakaopay.service.FindService;
import com.test.kakaopay.service.FindServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.test.kakaopay.model.transaction.MemoryTransactionRepository;
import com.test.kakaopay.model.transaction.TransactionRepository;
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
