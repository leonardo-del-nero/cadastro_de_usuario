package br.com.xerox.teste.dto;

import java.io.Serializable;

import br.com.xerox.teste.common.View;
import br.com.xerox.teste.validation.MenuCreate;
import br.com.xerox.teste.validation.MenuUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MenuCreate(groups = View.Create.class)
@MenuUpdate(groups = View.Update.class)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MenuDTO implements Serializable {

    @EqualsAndHashCode.Include
    private long id;

    @NotBlank(message = "O campo label é obrigatório", groups = {View.Create.class, View.Update.class})
    private String label;

    @NotBlank(message = "O campo url é obrigatório", groups = {View.Create.class, View.Update.class})
    private String url;
}
