package file;

import java.util.Objects;

public class Pessoa {
	private String nome;
	private String sobrenome;
	private String email;
	private String telefone;
	private String cidade;
	
	
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", sobrenome=" + sobrenome
				+ ", cidade=" + cidade + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(cidade, email, nome, sobrenome, telefone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(email, other.email)
				&& Objects.equals(nome, other.nome) && Objects.equals(sobrenome, other.sobrenome)
				&& Objects.equals(telefone, other.telefone);
	}
	
	

}
