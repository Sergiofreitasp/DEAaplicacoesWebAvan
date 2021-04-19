package br.com.cbgomes.acme.client.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMensage {
	private Date currentdate;
	private String description;
	//private String msg;
}
