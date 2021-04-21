package br.com.cbgomes.acme.client.domain.dto;

import br.com.cbgomes.acme.client.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor //precisa do contrutor vazio para inicializar p ClientDTO
@Data
//@Builder
public class ClientDTO {
	
	private String name;

	private String email;

	private String phone;
	
	/*
	 * public static Client covertDTOtoClient(ClientDTO dto) { return Client
	 * .builder() .name(dto.name) .email(dto.email) .phone(dto.phone).build(); }
	 * 
	 * public static ClientDTO covertDTOtoClient(Client client) { return ClientDTO
	 * .builder() .name(client.getName()) .email(client.getEmail())
	 * .phone(client.getPhone()).build(); }
	 */
	
}
