package executavel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
	
public class DatasEmJava {
	public static void main(String[] args) throws ParseException, InterruptedException {
		Date data = new Date();
		Calendar calendario = Calendar.getInstance();
		SimpleDateFormat dataFormatadaTela = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dataFormatadaBd = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(data);

		System.out.println("Data formatada: " + dataFormatadaTela.format(data));
		System.out.println("Data calendar formatada: " + dataFormatadaBd.format(calendario.getTime()));

		// comparar datas
		Date dataVencimento = dataFormatadaTela.parse("20/06/2022");
		Date dataAtual = dataFormatadaTela.parse("24/06/2022");

		// data 1 (dataVencimento) menor que a data 2 (dataAtual)
		if (dataVencimento.before(dataAtual)) {
			System.out.println("Boleto nao vencido");
		} else {
			System.out.println("Boleto vencido");
		}

		// calcular quantos dias vencidos
		long dias = ChronoUnit.DAYS.between(LocalDate.parse("2022-06-14"), LocalDate.now());
		System.out.println("Boleto esta vencido ha " + dias + " dias.");

		// gerar parcelas de vencimento
		Date dataInicial = new SimpleDateFormat("dd/MM/yyyy").parse("24/06/2022"); // data inicial
		Calendar calendar = Calendar.getInstance();
		int qtdeParcelas = 10;

		for (int i = 0; i < qtdeParcelas; i++) {
			calendar.add(Calendar.MONTH, 1); // pula 1 mês
			System.out.println("Boleto " + (i + 1) + " com vencimento para "
					+ new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime()));
		}

		// nova api java 8+
		LocalDateTime dataNovaAtual = LocalDateTime.now();
		System.out.println("Data atual: " + dataNovaAtual);
		System.out.println(
				"Data atual formatada: " + dataNovaAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

		// verificar qto tempo esta demorando para executar uma tarefa
		Instant inicio = Instant.now();

		// aqui inicia a tarefa que será avaliada
		Thread.sleep(2000);

		Instant iFinal = Instant.now();
		Duration duracao = Duration.between(inicio, iFinal);

		System.out.println("Tempo em milisegundos para executar a tarefa " + duracao.toMillis());

		// Classe periodo
		LocalDate oldDtade = LocalDate.parse("2013-01-12");
		LocalDate newDtade = LocalDate.parse("2022-06-24");

		Period periodo = Period.between(oldDtade, newDtade);
		System.out.println("Periodo entre datas = " + periodo.getYears() + " anos, " + periodo.getMonths() + " meses e "
				+ periodo.getDays() + " dias.");

		/* gerando datas de vencimento com a classe LocalDate
		 * imprime saída formatada
		 */
		LocalDate dataBase = LocalDate.parse("2022-06-24");

		for (int i = 0; i < 10; i++) {
			System.out.println("Boleto " + (i + 1) + " com vencimento para: "
					+ dataBase.plusMonths(i + 1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
	}
}
