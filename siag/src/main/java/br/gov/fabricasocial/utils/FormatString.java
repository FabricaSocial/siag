package br.gov.fabricasocial.utils;

public class FormatString {

	public String unformatCPF(String cpf) {
		String unformattedCPF = cpf.replaceAll("[.-]", "");
		return unformattedCPF;
	}
	
	public String formatCPF(String cpf) {
		String formattedCPF;
		
		try{
			cpf.subSequence(9, 11);
		} catch (StringIndexOutOfBoundsException e) {
			cpf = "0" + cpf;
		} finally {
			formattedCPF = 	(String) cpf.subSequence(0, 3) + "." +
					cpf.subSequence(3, 6) + "." + cpf.subSequence(6, 9) +
					"-" + cpf.subSequence(9, 11);
		}
		return formattedCPF;
	}
}
