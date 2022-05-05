package me.dio.academia.digital.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.repository.AlunoRepository;



@Controller
public class AlunoController {

	@Autowired
	private AlunoRepository ar;
	
	@RequestMapping(value="/aluno/listar", method = RequestMethod.GET )
	public ModelAndView form(Aluno aluno) 
	{
		
		ModelAndView mv = new ModelAndView("formAluno");
		return mv;
	}
	
	
	@RequestMapping(value="/aluno/adicionar",method=RequestMethod.POST)
	public ModelAndView adicionar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes, Model model) 
	{
		
		if(result.hasErrors())
		{
			return form(aluno);
		}
		
		try
		{
			ar.save(aluno);
		}
		catch (Exception e) 
		{
			
			System.out.println(e.getMessage());
			
			result.reject("Erro", "Não foi possível salvar o aluno.");
			model.addAttribute("aluno", aluno);
			ModelAndView mv = new ModelAndView("formAluno");
			return mv;
			
		}
		
		ModelAndView mv = new ModelAndView("redirect:/aluno/listar");
		attributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
		return mv;

	}
	
	@RequestMapping(value="/aluno/editar/{id}", method = RequestMethod.GET )
	public ModelAndView buscar(@PathVariable("id") Long id, Model model) 
	{

		
		
		model.addAttribute("aluno", ar.findById(id).get());
		ModelAndView mv = new ModelAndView("formEditAluno");
		return mv;

	}
	
	@RequestMapping(value="/aluno/editar",method=RequestMethod.POST)
	public ModelAndView editar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attributes, Model model) 
	{

		

		if(result.hasErrors())
		{
			
			model.addAttribute("aluno", aluno);
			ModelAndView mv = new ModelAndView("formEditAluno");
			return mv;
			
		}

		
		try
		{
			ar.save(aluno);
		}
		catch (Exception e) {
			
			
			System.out.println(e.getMessage());
			
			result.reject("Erro", "Não foi possível editar o aluno.");
			model.addAttribute("aluno", aluno);
			ModelAndView mv = new ModelAndView("formEditAluno");
			return mv;
		}
		
		
		ModelAndView mv = new ModelAndView("redirect:/aluno/listar");
		attributes.addFlashAttribute("mensagem", "Aluno editado com sucesso!");
		return mv;

	}
	
	
	
	@ModelAttribute("todosAlunos")
	public List<Aluno> todosAlunos()
	{
		List<Aluno> alunos = ar.findAll();
		
		return alunos;
	}
	
	
}
