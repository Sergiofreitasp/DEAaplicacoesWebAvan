package br.com.cbgomes.acme.client.domain.dto;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.cbgomes.acme.client.domain.CourrentTransaction;
import br.com.cbgomes.acme.client.domain.SavingsTransaction;

@Component
public class TransactionConverterDTO {

	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	public static List<TransactionDTO> converterListCourrentTransaction(List<CourrentTransaction> cTransaction){
		return cTransaction
				.stream()
				.map(c -> modelMapper().map(c, TransactionDTO.class))
				.collect(Collectors.toList());
	}
	
	public static List<TransactionDTO> converterListSavingsTransaction(List<SavingsTransaction> sTransaction){
		return sTransaction
				.stream()
				.map(c -> modelMapper().map(c, TransactionDTO.class))
				.collect(Collectors.toList());
	}

}

