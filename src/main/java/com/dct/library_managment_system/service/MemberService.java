package com.dct.library_managment_system.service;

import com.dct.library_managment_system.entity.Members;
import jakarta.validation.Valid;

import java.util.List;

public interface MemberService {
    List<Members> fetchAllMembers();

    Members addMembers(@Valid Members member);

    Members fetchMemberById(Long id);

    List<Members> fetchAllMembersByName(String name);

    boolean deleteMemberById(long id);

    Members updateMemberById(long id, Members member);
}
