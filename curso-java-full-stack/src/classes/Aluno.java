package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import constantes.StatusAlunos;
import entidades.PermiteAcesso;

public class Aluno extends Pessoa implements PermiteAcesso {
	private String dataMatricula, nomeEscola, serieMatriculado, login, senha;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
	public Aluno(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public Aluno() {
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
	
	public String getAlunoAprovado() {
		float media = getMediaNota();
		if (media > 50) {
			if (media < 70) {
				return StatusAlunos.RECUPERACAO;
			} else {
				return StatusAlunos.APROVADO;
			}
		} else {
			return StatusAlunos.REPROVADO;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataMatricula, disciplinas, nomeEscola, serieMatriculado);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(dataMatricula, other.dataMatricula) && Objects.equals(disciplinas, other.disciplinas)
				&& Objects.equals(nomeEscola, other.nomeEscola)
				&& Objects.equals(serieMatriculado, other.serieMatriculado);
	}
	@Override
	public String toString() {
		return "Aluno [dataMatricula=" + dataMatricula + ", nomeEscola=" + nomeEscola + ", serieMatriculado="
				+ serieMatriculado + ", disciplinas=" + disciplinas + "]";
	}
	
	@Override
	public boolean autenticar(String login, String senha) {
		this.login = login;
		this.senha = senha;
		return autenticar();
	}
	@Override
	public boolean autenticar() {
		return login.equals("admin") && senha.equals("admin");
	}
	
}
