package br.com.cbgomes.acme.client.domain.dto;

import java.util.Date;

import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.SavingsAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDTO {


	private double value;

	private Date date;

	private AccountDTO accont;
	

	
}
