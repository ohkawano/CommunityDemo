package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Talk;
import com.example.demo.repository.TalkRepository;

@Controller
public class AdministratorController {

	private final TalkRepository talkRepository;

	public AdministratorController(TalkRepository talkRepository) {
		this.talkRepository = talkRepository;
	}

	@GetMapping("/login")
	public String login() {
		return "adminisratorLogin";
	}

	@PostMapping("/adminisratorTimeLine")
	public String adminisratorTimeLine(@ModelAttribute Talk talk, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		return "adminisratorTimeLine";
	}

	@PostMapping("/adminisratorAdd")
	public String adminisratorAdd(@Validated @ModelAttribute Talk talk, BindingResult result, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		if (result.hasErrors()) {
			return "redirect:/adminisratorTimeLine";
		}
		talkRepository.save(talk);
		return "redirect:/adminisratorTimeLine";
	}

	@GetMapping("/adminisratorDelete")
	public String adminisratorDelete(@Validated @ModelAttribute Talk talk, BindingResult result, Model model) {
		model.addAttribute("talks", talkRepository.findAll());
		talkRepository.deleteById(talk.getId());
		return "redirect:/adminisratorTimeLine";
	}

}
