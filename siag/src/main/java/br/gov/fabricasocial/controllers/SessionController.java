package br.gov.fabricasocial.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.gov.fabricasocial.models.User;

public class SessionController {
	
	public void startSession(HttpServletRequest request,User user) {
		boolean create = true;
		HttpSession session = request.getSession(create);
		
		session.setAttribute("loggedUser", user.getUsername());		
	}

	public void finishSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	public boolean checkSession(HttpServletRequest request) {
		boolean check = false;
		HttpSession session = request.getSession(check);
		String loggedUser = (String) session.getAttribute("loggedUser");

		if(loggedUser != null){
			return true;
		} else {
			return false;
		}
	}
	
	public String getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String loggedUser = (String) session.getAttribute("loggedUser");
		
		return loggedUser;
	}
}