package aula1;

public class aula1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Aluno aluno = new Aluno();
		Disciplina disciplina1 = new Disciplina();
		
		aluno.setCep("321654654");
		aluno.setCpf("3215643132");
		aluno.setNome("Carlos Mangueira");
		
		disciplina1.setNota(80);
		disciplina1.setNomeDisciplina("Matematica");
		aluno.getDisciplina().add(disciplina1);
		
		Disciplina disciplina2 = new Disciplina();
		disciplina2.setNomeDisciplina("Português");
		disciplina2.setNota(50);
		aluno.getDisciplina().add(disciplina2);
		
		Disciplina disciplina3 = new Disciplina();
		disciplina3.setNomeDisciplina("História");
		disciplina3.setNota(92);
		aluno.getDisciplina().add(disciplina3);
		
		System.out.printf("\nA soma das notas é: %f",aluno.somaNotas());
		System.out.printf("\nA média final das notas é: %f",aluno.getMediaNota());
		
		System.out.println("\n" + aluno);
		
	}
}
