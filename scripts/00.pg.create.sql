CREATE sequence hibernate_sequence START 1;

CREATE TABLE t_utilisateur_ut (
    ut_id bigint not null,
    ut_nom text not null,
    ut_prenom text not null,
    ut_login text not null,
    ut_password text not null,
    ut_email text not null,
    ut_actif text not null,
    creation_date timestamp not null,
    creation_user text,
    update_date timestamp,
    update_user text,
    CONSTRAINT pk_t_utilisateur_ut PRIMARY KEY(ut_id),
    CONSTRAINT un_t_utilisateur_ut_login UNIQUE(ut_login)
);

CREATE TABLE t_authentification_au (
    au_id text not null,
    ut_id bigint not null,
    au_date_creation timestamp not null,
    au_date_derniere_activite timestamp not null,
    creation_date timestamp not null,
    creation_user text,
    update_date timestamp,
    update_user text,
    CONSTRAINT pk_t_authentification_au PRIMARY KEY(au_id),
    CONSTRAINT fk_t_authentification_au_ut FOREIGN KEY(ut_id) REFERENCES t_utilisateur_ut(ut_id)
);

CREATE TABLE t_entite_en (
    en_id bigint not null,
    parent_id bigint,
    en_type text not null,
    en_nom text not null,
    en_adresse text,
    en_adresse_compl text,
    en_code_postal text,
    en_ville text,
    en_email text,
    en_telephone text,
    en_site_web text,
    creation_date timestamp not null,
    creation_user text,
    update_date timestamp,
    update_user text,
    CONSTRAINT pk_t_entite_en PRIMARY KEY(en_id),
    CONSTRAINT ck_t_entite_en_parent CHECK (en_id <> parent_id),
    CONSTRAINT ck_t_entite_en_type CHECK (en_type IN ('FEDERATION', 'LIGUE_REG', 'COMITE_DEP', 'ASSOCIATION', 'SECTION', 'GROUPE')),
    CONSTRAINT fk_entite_parente FOREIGN KEY (parent_id) REFERENCES t_entite_en (en_id)
);