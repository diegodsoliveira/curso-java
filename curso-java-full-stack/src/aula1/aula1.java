package aula1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import Constantes.StatusAlunos;

public class aula1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		List<Aluno> alunos = new ArrayList<Aluno>();
		HashMap<String, List<Aluno>> maps = new HashMap<String, List<Aluno>>();
		
		for (int i = 1; i <= 5; i++ ) {
			Aluno aluno = new Aluno();
			
			/* Recebe os dados do aluno */
			String nome = JOptionPane.showInputDialog("Informe o nome do aluno "+i+":");
			aluno.setNome(nome);
			/*String cep = JOptionPane.showInputDialog("Informe o cep do aluno "+i+":");
			aluno.setCep(cep);
			String cpf = JOptionPane.showInputDialog("Informe o cpf do aluno "+i+":");
			aluno.setCpf(cpf);*/
	
			/* Recebe as disciplinas do aluno */
			for (int qtde = 1; qtde <= 2; qtde++) {
				Disciplina disciplina = new Disciplina();
				String nomeDisciplina = JOptionPane.showInputDialog("Informe a disciplina "+qtde);
				disciplina.setNomeDisciplina(nomeDisciplina);
				String nota = JOptionPane.showInputDialog("Informe a nota da disciplina "+qtde);
				disciplina.setNota(Float.parseFloat(nota));
				aluno.getDisciplina().add(disciplina);
			}
			alunos.add(aluno);
		
		}
		/* Inicializa as listas */
		maps.put(StatusAlunos.APROVADO, new ArrayList<Aluno>());
		maps.put(StatusAlunos.REPROVADO, new ArrayList<Aluno>());
		maps.put(StatusAlunos.RECUPERACAO, new ArrayList<Aluno>());
		
		/* Verifica status dos alunos e separa em listas*/
		for (Aluno aluno : alunos) {
			if (aluno.getAlunoAprovado().equalsIgnoreCase(StatusAlunos.APROVADO)) {
				maps.get(StatusAlunos.APROVADO).add(aluno);
			} else 
				if (aluno.getAlunoAprovado().equalsIgnoreCase(StatusAlunos.RECUPERACAO)) {
					maps.get(StatusAlunos.RECUPERACAO).add(aluno);
			} else if (aluno.getAlunoAprovado().equalsIgnoreCase(StatusAlunos.REPROVADO)) {
				maps.get(StatusAlunos.REPROVADO).add(aluno);
			}
		}
		
		for (Aluno aluno : alunos) {
			System.out.println("O aluno " + aluno.getNome() + " cursou as seguintes disciplinas:");
			for (Disciplina disciplina : aluno.getDisciplina()) {
				System.out.println("Materia: " + disciplina.getNomeDisciplina() + " nota: " + disciplina.getNota());
			}
			System.out.println("A soma das notas é: " + aluno.somaNotas());
			System.out.println(aluno.getAlunoAprovado());
			//System.out.println("A média final das notas é: " + aluno.getMediaNota());
			System.out.println("------------------------------------");
		}

		System.out.println("Alunos aprovados");
		for (Aluno aluno : maps.get(StatusAlunos.APROVADO)) {
			System.out.println(aluno.getNome() + ": " + aluno.getAlunoAprovado() + " com media final: " + aluno.getMediaNota());
		}
		
		System.out.println("Alunos em recuperacao");
		System.out.println("------------------------------------");
		for (Aluno aluno : maps.get(StatusAlunos.RECUPERACAO)) {
			System.out.println(aluno.getNome() + ": " + aluno.getAlunoAprovado() + " com media final: " + aluno.getMediaNota());
		}
		
		System.out.println("Alunos reprovados");
		System.out.println("------------------------------------");
		for (Aluno aluno : maps.get(StatusAlunos.REPROVADO)) {
			System.out.println(aluno.getNome() + ": " + aluno.getAlunoAprovado() + " com media final: " + aluno.getMediaNota());
		}
		
		System.out.println(alunos);
		
	}
}
