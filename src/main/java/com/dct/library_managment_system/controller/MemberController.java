package com.dct.library_managment_system.controller;

import com.dct.library_managment_system.entity.Members;
import com.dct.library_managment_system.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Members>> fetchAllMembers() {
        return ResponseEntity.ok(memberService.fetchAllMembers());
    }

    @PostMapping("/add")
    public ResponseEntity<Members> addMembers(@Valid @RequestBody Members member) {
        Members mem = memberService.addMembers(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(mem);
    }

    @GetMapping("/id")
    public ResponseEntity<Members> fetchMemberById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.fetchMemberById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<Members>> fetchAllMemberByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.fetchAllMembersByName(name));
    }

    @PutMapping("/id")
    public ResponseEntity<Members> updateMemberById(@RequestParam long id,
                                                    @RequestBody Members member) {
        return ResponseEntity.ok(memberService.updateMemberById(id, member));
    }

    @DeleteMapping("id")
    public ResponseEntity<String> deleteMemberById(@RequestParam long id) {
        if (memberService.deleteMemberById(id)) {
            return ResponseEntity.ok("Member is delete successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member-id not found");
    }
}
