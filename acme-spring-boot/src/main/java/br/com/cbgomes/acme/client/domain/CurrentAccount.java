package br.com.cbgomes.acme.client.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Data
@Entity
@Table(name= "tb_currentAccount")
public class CurrentAccount {

	private static final long serialVersionUID = 1L;
	
	
}
