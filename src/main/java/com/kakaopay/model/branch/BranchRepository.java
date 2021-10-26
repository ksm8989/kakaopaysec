package com.kakaopay.model.branch;


import java.util.Map;

public interface BranchRepository {
    void save(Branch branch);

    Branch findByCode(String code);

    Map<String, Branch>  findAllBranch();

}
