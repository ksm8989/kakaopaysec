package com.test.kakaopay.model.branch;

import java.util.HashMap;
import java.util.Map;

public class MemoryBranchRepository implements BranchRepository {

    private static Map<String, Branch> store = new HashMap<>();

    @Override
    public void save(Branch branch) {
        store.put(branch.getCode(), branch);
    }

    @Override
    public Branch findByCode(String code) {
        return store.get(code);
    }

    @Override
    public Map<String, Branch> findAllBranch() {
        return store;
    }

}
