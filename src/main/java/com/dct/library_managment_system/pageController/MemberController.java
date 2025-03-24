//package com.dct.library_managment_system.pageController;
//
//import com.dct.library_managment_system.entity.Members;
//import com.dct.library_managment_system.error.BookNotFound;
//import com.dct.library_managment_system.error.MemberNotFound;
//import com.dct.library_managment_system.service.MemberService;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/members")
//public class MemberController {
//
//    private final MemberService memberService;
//
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
//
//    @GetMapping("/")
//    public String fetchAllMembers(Model model) {
//        List<Members> members = memberService.fetchAllMembers();
//        model.addAttribute("members", members);
//        return "members/member_list";
//    }
//
//    @GetMapping("/add")
//    public String showAddMemberForm(Model model) {
//        model.addAttribute("member", new Members());
//        return "members/add_member";
//    }
//
//    @PostMapping("/add")
//    public String addMember(@Valid @ModelAttribute("member") Members member, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "members/add_member";
//        }
//        try {
//            memberService.addMembers(member);
//            return "redirect:/members/";
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "members/add_member";
//        }
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditMemberForm(@PathVariable("id") long id, Model model) {
//        try {
//            Members member = memberService.fetchMemberById(id);
//            model.addAttribute("member", member);
//            return "members/edit_member";
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found", e);
//        }
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateMember(@PathVariable("id") long id, @Valid @ModelAttribute("member") Members member, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "members/edit_member";
//        }
//        try {
//            memberService.updateMemberById(id, member);
//            return "redirect:/members/";
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("errorMessage", e.getMessage());
//            return "members/edit_member";
//        }
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteMember(@PathVariable("id") long id) {
//        memberService.deleteMemberById(id);
//        return "redirect:/members/";
//    }
//
//    @ExceptionHandler(MemberNotFound.class)
//    public String handleBookNotFoundException(MemberNotFound ex, Model model) {
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "error";  // Return the name of your error template
//    }
//}