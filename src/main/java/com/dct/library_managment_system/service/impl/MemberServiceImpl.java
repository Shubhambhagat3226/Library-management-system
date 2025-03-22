package com.dct.library_managment_system.service.impl;

import com.dct.library_managment_system.entity.Members;
import com.dct.library_managment_system.error.MemberNotFound;
import com.dct.library_managment_system.repository.MembersRepository;
import com.dct.library_managment_system.service.MemberService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


@Service
public class MemberServiceImpl implements MemberService {

    private final MembersRepository membersRepo;

    /* Email validation regex */
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    /* Phone number validation regex (only digits, exactly 10 characters) */
    private static final String PHONE_REGEX = "^[0-9]{10}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public MemberServiceImpl(MembersRepository membersRepository) {
        this.membersRepo = membersRepository;
    }

    @Override
    public List<Members> fetchAllMembers() {
        return membersRepo.findAll();
    }

    @Override
    public Members addMembers(Members member) {
        try {
            return membersRepo.save(member);
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalArgumentException("This email is already in use. Please use a different email.");
        }
    }

    @Override
    public Members fetchMemberById(Long id) throws MemberNotFound{
        Optional<Members> members = membersRepo.findById(id);

        if (!members.isPresent()) {
            throw new MemberNotFound("id is invalid");
        }
        return members.get();
    }

    @Override
    public List<Members> fetchAllMembersByName(String name) throws MemberNotFound {

        Optional<List<Members>> membersList = membersRepo.findAllByNameContaining(name);

        if (!membersList.isPresent()) {
            throw new MemberNotFound("This name member is not found");
        }

        return membersList.get();
    }

    @Override
    public boolean deleteMemberById(long id) {
        if (!membersRepo.existsById(id)) {
            return false;
        }

        membersRepo.deleteById(id);
        return true;
    }

    @Override
    public Members updateMemberById(long id, Members newMember) {
        if (!membersRepo.existsById(id)) {
            throw new MemberNotFound("Member-id not found");
        }

        Members member = membersRepo.findById(id).get();

        /* member name */
        if (newMember.getName() != null &&
                !newMember.getName().isBlank() &&
                !newMember.getName().equalsIgnoreCase(member.getName())
        ) {
            member.setName(newMember.getName());
        }

        /* member email-id */
        if (newMember.getEmail() != null &&
                !newMember.getEmail().isBlank() &&
                !newMember.getEmail().equalsIgnoreCase(member.getEmail())
        ) {
            // Validate email format
            Matcher matcher = EMAIL_PATTERN.matcher(newMember.getEmail());
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid email format. Please enter a valid email address.");
            }

            if (membersRepo.existsByEmail(newMember.getEmail())) {
                throw new MemberNotFound("This email-id already exist");
            }
            member.setEmail(newMember.getEmail());
        }

        /* member phone-no */
        if (newMember.getPhoneNumber() != null &&
                !newMember.getPhoneNumber().isBlank()
        ) {
            // Validate phone number format
            Matcher matcher = PHONE_PATTERN.matcher(newMember.getPhoneNumber());
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Phone number is invalid. It must be exactly 10 digits.");
            }

            member.setPhoneNumber(newMember.getPhoneNumber());
        }

        return membersRepo.save(member);

    }


}
