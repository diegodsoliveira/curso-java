package aula1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aluno {
	private String nome;
	private String cpf;
	private String cep;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Disciplina> getDisciplina() {
		return disciplinas;
	}
	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplinas = disciplina;
	}
	public float somaNotas() {
		float notas = 0;
		for (Disciplina disciplina : disciplinas) {
			notas += disciplina.getNota();
		}
		return notas; 
	}
	
	public float getMediaNota() {
		return somaNotas() / disciplinas.size();
	}
	@Override
	public int hashCode() {
		return Objects.hash(cep, cpf, disciplinas, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(disciplinas, other.disciplinas) && Objects.equals(nome, other.nome);
	}
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", cpf=" + cpf + ", cep=" + cep + ", disciplinas=" + disciplinas + "]";
	}
	

	
	
	
}
