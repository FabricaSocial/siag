package br.gov.fabricasocial.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.gov.fabricasocial.models.User;

/**
 * Gerenciamento de sessao
 * @author DETI - Departamento de Tecnologia de Informacao
 *
 */
public class SessionController {
	
	/**
	 * Inicia sessao
	 * @param user Usuario que efetuou login
	 */
	public void startSession(HttpServletRequest request,User user) {
		boolean create = true;
		HttpSession session = request.getSession(create);
		
		session.setAttribute("loggedUser", user.getUsername());
		session.setAttribute("loggedUserId", user.getIdUser());
	}

	/**
	 * Termina sessao
	 */
	public void finishSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	/**
	 * Verifica existe uma sessao
	 * @return true caso exista e false se nao existir
	 */
	public boolean checkSession(HttpServletRequest request) {
		boolean check = false;
		HttpSession session = request.getSession(check);
		String loggedUser = "";
		try{
			loggedUser = (String) session.getAttribute("loggedUser");
		} catch (NullPointerException e) {
			loggedUser = null;
		}

		if(loggedUser != null){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Obtem usuario logado no sistema
	 * @return Nome do usuario logado
	 */
	public String getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String loggedUser = (String) session.getAttribute("loggedUser");
		
		return loggedUser;
	}
	
	/**
	 * Obtem ID do usuario logado no sistema
	 * @return ID do usuario logado
	 */
	public int getLoggedUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int loggedUserId = (Integer) session.getAttribute("loggedUserId");
		
		return loggedUserId;
	}
}
