package br.gov.fabricasocial.utils;

public class FormatString {

	public String unformatCPF(String cpf) {
		String unformattedCPF = cpf.replaceAll("[.-]", "");
		return unformattedCPF;
	}
	
	public String formatCPF(String cpf) {

		String formattedCPF = 	(String) cpf.subSequence(0, 3) + "." +
								cpf.subSequence(3, 6) + "." + cpf.subSequence(6, 9) +
								"-" + cpf.subSequence(9, 11);

		return formattedCPF;
	}
}
