package br.com.cbgomes.acme.client.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import br.com.cbgomes.acme.client.domain.Account;

public class AccountConverterDTO {

	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	public static List<AccountDTO> conveterListAccount(List<Account> accounts){
		return accounts
				.stream()
				.map(c -> modelMapper().map(c, AccountDTO.class))
				.collect(Collectors.toList());
	}
	
	public static Account convertToAccount(AccountDTO dto) {
		return modelMapper().map(dto, Account.class);
	}
	
	public static AccountDTO convertToAccountDTO(Object object) {
		return modelMapper().map(object, AccountDTO.class);
	}
	

}

