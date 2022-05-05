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


import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.Matricula;

import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.repository.MatriculaRepository;

@Controller
public class AvaliacaoFisicaController {
	
	@Autowired
	private MatriculaRepository mr;
	
	@Autowired
	private AvaliacaoFisicaRepository afr;
	
	@RequestMapping(value="/avaliacaoFisica/listar", method = RequestMethod.GET )
	public ModelAndView form(AvaliacaoFisica avaliacaoFisica) 
	{
		
		ModelAndView mv = new ModelAndView("formAvaliacaoFisica");
		return mv;
	}
	
	
	@RequestMapping(value="/avaliacaoFisica/adicionar",method=RequestMethod.POST)
	public ModelAndView adicionar(@Valid AvaliacaoFisica avaliacaoFisica, BindingResult result, RedirectAttributes attributes, Model model) 
	{
		
		if(result.hasErrors())
		{
			return form(avaliacaoFisica);
		}
		
		try
		{
			afr.save(avaliacaoFisica);
		}
		catch (Exception e) 
		{
			
			System.out.println(e.getMessage());
			
			result.reject("Erro", "Não foi possível salvar a Avaliação Física.");
			model.addAttribute("avaliacaoFisica", avaliacaoFisica);
			ModelAndView mv = new ModelAndView("formAvaliacaoFisica");
			return mv;
			
		}
		
		ModelAndView mv = new ModelAndView("redirect:/avaliacaoFisica/listar");
		attributes.addFlashAttribute("mensagem", "Avaliação Física salva com sucesso!");
		return mv;

	}
	
	@RequestMapping(value="/avaliacaoFisica/editar/{id}", method = RequestMethod.GET )
	public ModelAndView buscar(@PathVariable("id") Long id, Model model) 
	{

		
		
		model.addAttribute("avaliacaoFisica", afr.findById(id).get());
		ModelAndView mv = new ModelAndView("formEditAvaliacaoFisica");
		return mv;

	}
	
	@RequestMapping(value="/avaliacaoFisica/editar",method=RequestMethod.POST)
	public ModelAndView editar(@Valid AvaliacaoFisica avaliacaoFisica, BindingResult result, RedirectAttributes attributes, Model model) 
	{

		

		if(result.hasErrors())
		{
			
			model.addAttribute("avaliacaoFisica", avaliacaoFisica);
			ModelAndView mv = new ModelAndView("formEditAvaliacaoFisica");
			return mv;
			
		}

		
		try
		{
			afr.save(avaliacaoFisica);
		}
		catch (Exception e) {
			
			
			System.out.println(e.getMessage());
			
			result.reject("Erro", "Não foi possível editar a Avaliação Física.");
			model.addAttribute("avaliacaoFisica", avaliacaoFisica);
			ModelAndView mv = new ModelAndView("formEditAvaliacaoFisica");
			return mv;
		}
		
		
		ModelAndView mv = new ModelAndView("redirect:/avaliacaoFisica/listar");
		attributes.addFlashAttribute("mensagem", "Avaliação Física editada com sucesso!");
		return mv;

	}
	
	
	@RequestMapping(value="/avaliacaoFisica/excluir",method=RequestMethod.POST)
	public ModelAndView excluir(Long idAvaliacaoExcluida, RedirectAttributes attributes, Model model) 
	{

		try
		{
			afr.deleteById(idAvaliacaoExcluida);
		}
		catch (Exception e) {
			
			
			System.out.println(e.getMessage());
			
			attributes.addFlashAttribute("mensagem", "Não foi possível editar a Avaliação Física.");
			ModelAndView mv = new ModelAndView("redirect:/avaliacaoFisica/listar");
			return mv;
		}
		
		
		ModelAndView mv = new ModelAndView("redirect:/avaliacaoFisica/listar");
		attributes.addFlashAttribute("mensagem", "Avaliação Física excluída com sucesso!");
		return mv;

	}
	
	@ModelAttribute("todasAvaliacoesFisicas")
	public List<AvaliacaoFisica> todasAvaliacoesFisicas()
	{
		List<AvaliacaoFisica> avaliacaoFisica = afr.findAll();
		
		return avaliacaoFisica;
	}
	
	@ModelAttribute("todasMatriculasHabilitadas")
	public List<Matricula> todasMatriculasHabilitadas()
	{
		List<Matricula> matriculas = mr.findByHabilitado(true);
		
		return matriculas;
	}
	

}