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
import me.dio.academia.digital.entity.Matricula;

import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;


@Controller
public class MatriculaController {
	
	@Autowired
	private MatriculaRepository mr;
	
	@Autowired
	private AlunoRepository ar;
	
	@RequestMapping(value="/matricula/listar", method = RequestMethod.GET )
	public ModelAndView form(Matricula matricula) 
	{
		
		ModelAndView mv = new ModelAndView("formMatricula");
		return mv;
	}
	
	
	@RequestMapping(value="/matricula/adicionar",method=RequestMethod.POST)
	public ModelAndView adicionar(@Valid Matricula matricula, BindingResult result, RedirectAttributes attributes, Model model) 
	{
		
		if(result.hasErrors())
		{
			return form(matricula);
		}
		
		try
		{
			mr.save(matricula);
		}
		catch (Exception e) 
		{
			
			System.out.println(e.getMessage());
			
			result.reject("Erro", "Não foi possível salvar a matrícula.");
			model.addAttribute("matricula", matricula);
			ModelAndView mv = new ModelAndView("formMatricula");
			return mv;
			
		}
		
		ModelAndView mv = new ModelAndView("redirect:/matricula/listar");
		attributes.addFlashAttribute("mensagem", "Matrícula salva com sucesso!");
		return mv;

	}
	
	@RequestMapping(value="/matricula/editar/{id}", method = RequestMethod.GET )
	public ModelAndView buscar(@PathVariable("id") Long id, Model model) 
	{

		
		
		model.addAttribute("matricula", mr.findById(id).get());
		ModelAndView mv = new ModelAndView("formEditMatricula");
		return mv;

	}
	
	@RequestMapping(value="/matricula/editar",method=RequestMethod.POST)
	public ModelAndView editar(@Valid Matricula matricula, BindingResult result, RedirectAttributes attributes, Model model) 
	{

		

		if(result.hasErrors())
		{
			
			model.addAttribute("matricula", matricula);
			ModelAndView mv = new ModelAndView("formEditMatricula");
			return mv;
			
		}

		
		try
		{
			mr.save(matricula);
		}
		catch (Exception e) {
			
			
			System.out.println(e.getMessage());
			
			result.reject("Erro", "Não foi possível editar a matrícula.");
			model.addAttribute("matricula", matricula);
			ModelAndView mv = new ModelAndView("formEditMatricula");
			return mv;
		}
		
		
		ModelAndView mv = new ModelAndView("redirect:/matricula/listar");
		attributes.addFlashAttribute("mensagem", "Matrícula editada com sucesso!");
		return mv;

	}
	
	@ModelAttribute("todosAlunos")
	public List<Aluno> todosAlunos()
	{
		List<Aluno> alunos = ar.findAll();
		
		return alunos;
	}
	
	@ModelAttribute("todasMatriculas")
	public List<Matricula> todasMatriculas()
	{
		List<Matricula> matriculas = mr.findAll();
		
		return matriculas;
	}
	

}
