package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created on 22/12/15.
 *
 * @author Denis
 */
public class LoginDTO extends AbstractDTO {

    @NotNull
    @Size(min = 3)
    private String login;

    @NotNull
    @Size(min = 5)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
