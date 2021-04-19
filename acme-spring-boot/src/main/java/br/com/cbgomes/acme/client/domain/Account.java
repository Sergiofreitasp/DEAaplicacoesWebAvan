package br.com.cbgomes.acme.client.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


public class Account {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 10, nullable = false)
	private String agencia;
	
	@Column(name = "num_conta", length = 15, nullable = false)
	private String numeroConta;
	
	@ManyToOne
	@JoinColumn(name = "fk_cliente_id", nullable = false)
	private Client cliente;
	
	private double saldo;
	
}
