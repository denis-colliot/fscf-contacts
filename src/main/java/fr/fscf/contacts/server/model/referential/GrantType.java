package fr.fscf.contacts.server.model.referential;

/**
 * Features grant types.
 *
 * @author Denis
 */
public enum GrantType {

    /**
     * Access granted to <em>anonymous</em> user <b>only</b>.
     */
    ANONYMOUS_ONLY,

    /**
     * Access granted to <em>authenticated</em> users <b>only</b>.
     */
    AUTHENTICATED_ONLY,

    /**
     * Access granted to <em>anonymous</em> <b>and</b> <em>authenticated</em> users.
     */
    BOTH;

}