package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>
 * Authentication domain entity.
 * </p>
 * The secure id of this Authentication, which is a 128-bit random number represented as a 32-character hexadecimal
 * string.
 *
 * @author Denis
 */
@javax.persistence.Entity
@Table(name = "t_authentification_au")
public class Authentication extends AbstractEntity<String> {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1653320385158332573L;

    /**
     * The secure id of this Authentication, which is a 128-bit random number represented as a 32-character hexadecimal
     * string.
     */
    @Id
    @Column(name = "au_id", unique = true, nullable = false, length = 128)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecureSequenceGenerator")
    @org.hibernate.annotations.GenericGenerator(name = "SecureSequenceGenerator", strategy = "fr.fscf.contacts.server.auth.SecureSequenceGenerator")
    @Size(max = 128)
    private String id;

    @Column(name = "au_date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "au_date_derniere_activite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastActive;

    // --------------------------------------------------------------------------------
    //
    // FOREIGN KEYS.
    //
    // --------------------------------------------------------------------------------

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ut_id", nullable = false)
    @NotNull
    private User user;

    // --------------------------------------------------------------------------------
    //
    // METHODS.
    //
    // --------------------------------------------------------------------------------

    public Authentication() {
        // Default empty constructor.
    }

    /**
     * Creates a new session object for the given {@code user}, with a secure session id and starting at the current
     * time.
     *
     * @param user
     *         The user.
     */
    public Authentication(final User user) {
        final Date now = new Date();
        setUser(user);
        setDateCreated(now);
        setDateLastActive(now);
    }

    // --------------------------------------------------------------------------------
    //
    // GETTERS & SETTERS.
    //
    // --------------------------------------------------------------------------------

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    private void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastActive() {
        return this.dateLastActive;
    }

    public void setDateLastActive(Date dateLastActive) {
        this.dateLastActive = dateLastActive;
    }

}