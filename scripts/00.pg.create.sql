CREATE sequence hibernate_sequence START 500;

-------------------------------------------
--
-- AUTHENTICATION & ACCESS RIGHTS TABLES.
--
-------------------------------------------

CREATE TABLE t_utilisateur_ut (
  ut_id bigint not null,
  ut_nom text not null,
  ut_prenom text not null,
  ut_email text not null,
  ut_password text not null,
  ut_actif boolean not null DEFAULT FALSE,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_utilisateur_ut PRIMARY KEY(ut_id),
  CONSTRAINT un_t_utilisateur_ut_email UNIQUE(ut_email)
);

CREATE TABLE t_authentification_au (
  au_id text not null,
  ut_id bigint not null,
  au_date_creation timestamp not null,
  au_date_derniere_activite timestamp not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_authentification_au PRIMARY KEY(au_id)
);

CREATE TABLE t_fonctionnalite_fe (
  fe_id bigint not null,
  fe_token text not null,
  fe_type_acces text not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
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
  de_id bigint,
  st_type text not null,
  st_nom text not null,
  st_email text,
  st_telephone text,
  st_site_web text,
  st_adresse text,
  st_adresse_compl text,
  st_code_postal text,
  st_ville text,
  st_cedex text,
  st_email2 text,
  st_telephone2 text,
  st_commentaire text,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_structure_st PRIMARY KEY(st_id),
  CONSTRAINT ck_t_structure_st_parent CHECK (st_id <> parent_id),
  CONSTRAINT ck_t_structure_st_type CHECK (st_type IN ('FEDERATION', 'COMMISSION_NATIONALE', 'LIGUE_REGIONALE',
                                                       'COMITE_DEPARTEMENTAL', 'ASSOCIATION', 'SECTION'))
);

CREATE TABLE t_contact_co (
  co_id bigint not null,
  co_nom text not null,
  co_prenom text not null,
  co_email text,
  co_telephone text,
  co_adresse text,
  co_adresse_compl text,
  co_code_postal text,
  co_ville text,
  co_cedex text,
  co_email2 text,
  co_telephone2 text,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_contact_co PRIMARY KEY(co_id)
);

CREATE TABLE t_fonction_fo (
  fo_id bigint not null,
  fo_nom text not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_fonction_fo PRIMARY KEY(fo_id),
  CONSTRAINT un_t_fonction_fo_nom UNIQUE(fo_nom)
);

CREATE TABLE t_affectation_af (
  co_id bigint not null,
  st_id bigint not null,
  fo_id bigint not null,
  af_fonction_detaillee text,
  af_statut text not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_affectation_af PRIMARY KEY(co_id, st_id, fo_id),
  CONSTRAINT ck_t_affectation_af_statut CHECK (af_statut IN ('BENEVOLE', 'SALARIE'))
);

-------------------------------------------
--
-- TERRITORIAL TABLES.
--
-------------------------------------------

CREATE TABLE t_region_re (
  re_id bigint not null,
  re_numero text not null,
  re_libelle text not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_region_re PRIMARY KEY(re_id),
  CONSTRAINT un_t_region_re_numero UNIQUE(re_numero)
);

CREATE TABLE t_departement_de (
  de_id bigint not null,
  re_id bigint not null,
  de_numero text not null,
  de_libelle text not null,
  creation_date timestamp not null DEFAULT now(),
  creation_user text,
  update_date timestamp,
  update_user text,
  CONSTRAINT pk_t_departement_de PRIMARY KEY(de_id),
  CONSTRAINT un_t_departement_de_numero UNIQUE(de_numero)
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
ALTER TABLE t_structure_st ADD CONSTRAINT fk_t_structure_st_department FOREIGN KEY (de_id) REFERENCES t_departement_de (de_id);

ALTER TABLE t_departement_de ADD CONSTRAINT fk_t_departement_de_region FOREIGN KEY (re_id) REFERENCES t_region_re (re_id);

ALTER TABLE t_affectation_af ADD CONSTRAINT fk_t_affectation_af_contact FOREIGN KEY (co_id) REFERENCES t_contact_co (co_id);
ALTER TABLE t_affectation_af ADD CONSTRAINT fk_t_affectation_af_structure FOREIGN KEY (st_id) REFERENCES t_structure_st (st_id);
ALTER TABLE t_affectation_af ADD CONSTRAINT fk_t_affectation_af_fonction FOREIGN KEY (fo_id) REFERENCES t_fonction_fo (fo_id);