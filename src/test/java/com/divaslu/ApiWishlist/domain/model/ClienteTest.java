package com.divaslu.ApiWishlist.domain.model;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.divaslu.ApiWishlist.model.Cliente;

public class ClienteTest {
	
	@Test
    public void criandoInstancia(){
        Cliente cliente = new ClienteBuilder().defaultValuescliente();
        assertThat(cliente).isNotNull();
     
}
   @Test
    public void comparandoAtributos(){
        Cliente cliente = new ClienteBuilder().defaultValuescliente();
        assertThat(cliente.getNomeCompleto()).isEqualTo(DefaultValuesCliente.NOMECOMPLETO);
        assertThat(cliente.getEmail()).isEqualTo(DefaultValuesCliente.EMAIL);
        assertThat(cliente.getTelefone()).isEqualTo(DefaultValuesCliente.TELEFONE);
    }

   

  
   }
   
