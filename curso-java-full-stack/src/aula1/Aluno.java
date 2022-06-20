package aula1;

import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import constantes.StatusAlunos;

public class Aluno extends Pessoa {
	private String dataMatricula;
	private String nomeEscola;
	private String serieMatriculado;
	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
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
}
