package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_categorias")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Categoria {
	
	@Id
	private int idcategoria;
	private String descripcion;
	
	
}
