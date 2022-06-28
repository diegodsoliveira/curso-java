package lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaStream {

	public static void main(String[] args) {
		List<Cliente> clientes = new ArrayList<>();
		
		clientes.add(new Cliente("Diego",38));
		clientes.add(new Cliente("Camila",25));
		clientes.add(new Cliente("Cristiane",32));
		clientes.add(new Cliente("Caio",21));
		clientes.add(new Cliente("Gabriel",23));
		clientes.add(new Cliente("João Pedro",11));
		clientes.add(new Cliente("Heitor",8));
		clientes.add(new Cliente("Guilherme",4));
		clientes.add(new Cliente("Antonio",62));
		
		List<Cliente> lista = clientes.stream()
				.filter(c -> c.getIdade() > 12)
				.collect(Collectors.toList()); //filtra os clientes que possuem idade maior que 12
		
		lista.forEach(l -> System.out.println(l.getIdade()));
		
		List<String> clientesFilter = clientes.stream()
				.map(c -> c.getNome())
				.collect(Collectors.toList()); // converte a lista de clientes para o tipo solicitado, no caso String, pois está pegando o nome
		
		clientesFilter.forEach(c -> System.out.println("Lista de clientes filter: " + c));
		
	}

}
