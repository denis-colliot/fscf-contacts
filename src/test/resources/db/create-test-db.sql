CREATE sequence hibernate_sequence START 1;

CREATE TABLE t_utilisateur_ut (
    ut_id bigint not null,
    ut_nom varchar not null,
    ut_prenom varchar not null,
    ut_login varchar not null,
    ut_password varchar not null,
    ut_email varchar not null,
    ut_actif varchar not null,
    creation_date timestamp not null,
    creation_user varchar,
    update_date timestamp,
    update_user varchar,
    CONSTRAINT pk_t_utilisateur_ut PRIMARY KEY(ut_id),
    CONSTRAINT un_t_utilisateur_ut_login UNIQUE(ut_login)
);

CREATE TABLE t_authentification_au (
    au_id varchar not null,
    ut_id bigint not null,
    au_date_creation timestamp not null,
    au_date_derniere_activite timestamp not null,
    creation_date timestamp not null,
    creation_user varchar,
    update_date timestamp,
    update_user varchar,
    CONSTRAINT pk_t_authentification_au PRIMARY KEY(au_id),
    CONSTRAINT fk_t_authentification_au_ut FOREIGN KEY(ut_id) REFERENCES t_utilisateur_ut(ut_id)
);


CREATE TABLE t_entite_en (
    en_id bigint not null,
    parent_id bigint,
    en_type varchar not null,
    en_nom varchar not null,
    en_adresse varchar,
    en_adresse_compl varchar,
    en_code_postal varchar,
    en_ville varchar,
    en_email varchar,
    en_telephone varchar,
    en_site_web varchar,
    creation_date timestamp not null,
    creation_user varchar,
    update_date timestamp,
    update_user varchar,
    CONSTRAINT pk_t_entite_en PRIMARY KEY(en_id),
    CONSTRAINT ck_t_entite_en_parent CHECK (en_id <> parent_id),
    CONSTRAINT ck_t_entite_en_type CHECK (en_type IN ('FEDERATION', 'LIGUE_REG', 'COMITE_DEP', 'ASSOCIATION', 'SECTION', 'GROUPE')),
    CONSTRAINT fk_entite_parente FOREIGN KEY (parent_id) REFERENCES t_entite_en (en_id)
);