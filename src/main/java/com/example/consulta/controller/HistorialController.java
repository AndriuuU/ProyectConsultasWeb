package com.example.consulta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.consulta.constantes.Constantes;
import com.example.consulta.model.HistorialModel;
import com.example.consulta.repository.HistorialRepository;
import com.example.consulta.service.ClienteService;
import com.example.consulta.service.HistorialService;

@Controller
@RequestMapping("/historial")
public class HistorialController {
	
	@Autowired
	@Qualifier("historialRepository")
	private HistorialRepository historialRepository;

	@Autowired
	@Qualifier("historialService")
	private HistorialService historialService;

	@Autowired
	@Qualifier("clienteService")
	private ClienteService clienteService;

	
	@GetMapping("/usuario")
	public ModelAndView registerForm(Model model) {
		ModelAndView mav = new ModelAndView(Constantes.OBTENER_HISTORIAL);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		long idCliente =clienteService.findByEmail(userEmail).getId();
		if(!userEmail.equalsIgnoreCase("anonymousUser")) {
			List<HistorialModel> lisHistorial = historialService.listHistorialCliente(idCliente);
		
			mav.addObject("historiales", lisHistorial);
		}

		return mav;
	}

}
