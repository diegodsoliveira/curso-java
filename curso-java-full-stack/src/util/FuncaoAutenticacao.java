package util;

import entidades.PermiteAcesso;

public class FuncaoAutenticacao {
	private PermiteAcesso acesso;
	
	public boolean autenticaUsuario() {
		return acesso.autenticar();
	}
	
	public FuncaoAutenticacao(PermiteAcesso acesso) {
		this.acesso = acesso;
	}
}
