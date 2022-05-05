package me.dio.academia.digital.entity;


import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_avaliacoes")
public class AvaliacaoFisica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Uma matrícula deve ser escolhida!")
	@ManyToOne
	@JoinColumn(name = "matricula_id")
	private Matricula matricula;
	
	
	private LocalDateTime dataDaAvaliacao = LocalDateTime.now();
	
	@NotNull(message = "Preencha o campo PESO corretamente.")
	@Positive(message = "'${validatedValue}' precisa ser positivo.")
	@Column(name = "peso_atual")
	private double peso;
	
	@NotNull(message = "Preencha o campo ALTURA corretamente.")
	@Positive(message = "'${validatedValue}' precisa ser positivo.")
	@Column(name = "altura_atual")
	private double altura;
	
	@Column(name = "imc_atual")
	private double imc;

	
	
	

}
