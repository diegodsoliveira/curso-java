package aula1;

import java.util.Objects;

public class Disciplina {
	private float nota;
	private String nomeDisciplina;
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nomeDisciplina, nota);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equals(nomeDisciplina, other.nomeDisciplina)
				&& Float.floatToIntBits(nota) == Float.floatToIntBits(other.nota);
	}
	@Override
	public String toString() {
		return "Disciplina [nota=" + nota + ", nomeDisciplina=" + nomeDisciplina + "]";
	}
	
	
}
