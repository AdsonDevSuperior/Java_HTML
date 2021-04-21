package com.ascenda2.demo.controller;

import com.ascenda2.demo.entity.Job;
import com.ascenda2.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class JobController {

    @Autowired
    JobRepository repository;

    @RequestMapping("/")
    public String listJobs(Model model) {
        model.addAttribute("jobs", repository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model) {
        model.addAttribute("job", new Job());
        return "jobForm";
    }

    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "jogForm";
        }
        repository.save(job);
        return "redirect:/";

    }
}