package br.gov.fabricasocial.dao.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import br.gov.fabricasocial.models.User;

public class LdapAuth {
	private Hashtable<String, String> authEnv = new Hashtable<String, String>(11);
	
	public LdapAuth() {
		// TODO Auto-generated constructor stub
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, "ldap://serv01");
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
	}
	
	public void authenticateUser(User user) throws NamingException, AuthenticationException {
		authEnv.put(Context.SECURITY_PRINCIPAL, "CIAS\\" + user.getUsername());
		authEnv.put(Context.SECURITY_CREDENTIALS, user.getPassword());
		
		@SuppressWarnings("unused")
		DirContext authContex = new InitialDirContext(authEnv);
	}
}
