package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }

        skillRepository.save(newSkill);

        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewEmployer(Model model, @PathVariable int skillId) {

        Optional otpSkill = skillRepository.findById(skillId);
        if (otpSkill.isPresent()) {
            Skill skill = (Skill) otpSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }

}

