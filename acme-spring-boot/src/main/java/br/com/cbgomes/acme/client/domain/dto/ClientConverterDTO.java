package br.com.cbgomes.acme.client.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.cbgomes.acme.client.domain.Client;
import lombok.RequiredArgsConstructor;

@Component
public class ClientConverterDTO {
	
	@Bean
	public static ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static Client converterToClient(ClientDTO dto) {
		return modelMapper().map(dto, Client.class);
	}
	
	public static List<ClientDTO> converterListClients(List<Client> clients){
		return clients
				.stream()
				.map(c -> modelMapper().map(c, ClientDTO.class))
				.collect(Collectors.toList());
	}
	
	public static ClientDTO converterToClientDTO(Client client) {
		return modelMapper().map(client, ClientDTO.class);
	}
}
