package aula1;

import java.util.Objects;

public class Aluno {
	private String nome;
	private float nota;
	private String cpf;
	private String cep;
	
	Disciplina disciplina = new Disciplina();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
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
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cep, cpf, disciplina, nome, nota);
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
				&& Objects.equals(disciplina, other.disciplina) && Objects.equals(nome, other.nome)
				&& Float.floatToIntBits(nota) == Float.floatToIntBits(other.nota);
	}
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", nota=" + nota + ", cpf=" + cpf + ", cep=" + cep + ", disciplina=" + disciplina
				+ "]";
	}
	
	
	
}
