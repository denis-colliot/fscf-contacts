package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntityAutoId;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Denis on 24/04/15.
 */
@javax.persistence.Entity
@Table(name = "t_utilisateur_ut")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ut_id", nullable = false))
})
public class User extends AbstractEntityAutoId<Long> {

    @Column(name = "ut_nom", nullable = false)
    private String name;

    @Column(name = "ut_prenom", nullable = false)
    private String firstName;

    @Column(name = "ut_login", nullable = false)
    private String login;

    @Column(name = "ut_password", nullable = false)
    private String password;

    @Column(name = "ut_email", nullable = true)
    private String email;

    @Column(name = "ut_actif", nullable = true)
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
