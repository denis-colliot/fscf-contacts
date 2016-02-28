CREATE sequence hibernate_sequence START 1;

-------------------------------------------
--
-- AUTHENTICATION & ACCESS RIGHTS TABLES.
--
-------------------------------------------

CREATE TABLE t_utilisateur_ut (
  ut_id bigint not null,
  ut_nom varchar not null,
  ut_prenom varchar not null,
  ut_email varchar not null,
  ut_password varchar not null,
  ut_actif boolean not null DEFAULT FALSE,
  creation_date timestamp not null DEFAULT now(),
  creation_user varchar,
  update_date timestamp,
  update_user varchar,
  CONSTRAINT pk_t_utilisateur_ut PRIMARY KEY(ut_id),
  CONSTRAINT un_t_utilisateur_ut_email UNIQUE(ut_email)
);

CREATE TABLE t_authentification_au (
  au_id varchar not null,
  ut_id bigint not null,
  au_date_creation timestamp not null,
  au_date_derniere_activite timestamp not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user varchar,
  update_date timestamp,
  update_user varchar,
  CONSTRAINT pk_t_authentification_au PRIMARY KEY(au_id)
);

CREATE TABLE t_fonctionnalite_fe (
  fe_id bigint not null,
  fe_token varchar not null,
  fe_type_acces varchar not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user varchar,
  update_date timestamp,
  update_user varchar,
  CONSTRAINT pk_t_fonctionnalite_fe PRIMARY KEY(fe_id),
  CONSTRAINT un_t_fonctionnalite_fe_token UNIQUE(fe_token),
  CONSTRAINT ck_t_fonctionnalite_fe_type_acces CHECK (fe_type_acces IN ('ANONYMOUS_ONLY', 'AUTHENTICATED_ONLY', 'BOTH'))
);

CREATE TABLE tr_habilitation_ha (
  ut_id bigint not null,
  fe_id bigint not null,
  st_id bigint not null,
  CONSTRAINT pk_tr_habilitation_ha PRIMARY KEY(ut_id, fe_id, st_id)
);

-------------------------------------------
--
-- FUNCTIONAL TABLES.
--
-------------------------------------------

CREATE TABLE t_structure_st (
  st_id bigint not null,
  parent_id bigint,
  st_type varchar not null,
  st_nom varchar not null,
  st_email varchar,
  st_telephone varchar,
  st_site_web varchar,
  st_adresse varchar,
  st_adresse_compl varchar,
  st_code_postal varchar,
  st_ville varchar,
  st_cedex varchar,
  st_email2 varchar,
  st_telephone2 varchar,
  st_commentaire varchar,
  creation_date timestamp not null DEFAULT now(),
  creation_user varchar,
  update_date timestamp,
  update_user varchar,
  CONSTRAINT pk_t_structure_st PRIMARY KEY(st_id),
  CONSTRAINT ck_t_structure_st_parent CHECK (st_id <> parent_id),
  CONSTRAINT ck_t_structure_st_type CHECK (st_type IN ('FEDERATION', 'COMMISSION_NATIONALE', 'LIGUE_REGIONALE',
                                                       'COMITE_DEPARTEMENTAL', 'ASSOCIATION', 'SECTION'))
);

-------------------------------------------
--
-- FOREIGN KEYS.
--
-------------------------------------------

ALTER TABLE t_authentification_au ADD CONSTRAINT fk_t_authentification_au_ut FOREIGN KEY(ut_id) REFERENCES t_utilisateur_ut(ut_id);
ALTER TABLE tr_habilitation_ha ADD CONSTRAINT fk_tr_habilitation_ha_ut FOREIGN KEY (ut_id) REFERENCES t_utilisateur_ut (ut_id);
ALTER TABLE tr_habilitation_ha ADD CONSTRAINT fk_tr_habilitation_ha_fe FOREIGN KEY (fe_id) REFERENCES t_fonctionnalite_fe (fe_id);
ALTER TABLE tr_habilitation_ha ADD CONSTRAINT fk_tr_habilitation_ha_st FOREIGN KEY (st_id) REFERENCES t_structure_st (st_id);

ALTER TABLE t_structure_st ADD CONSTRAINT fk_t_structure_st_parente FOREIGN KEY (parent_id) REFERENCES t_structure_st (st_id);