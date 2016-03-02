-------------------------------------------
--
-- TEST DATA.
--
-------------------------------------------

INSERT INTO t_utilisateur_ut (ut_id, ut_nom, ut_prenom, ut_email, ut_password) VALUES
(10, 'Stark', 'Tony', 'tony@starkindustries.com', 'ironman'),
(11, 'Wayne', 'Bruce', 'bruce@wayneindustries.com', 'batman'),
(12, 'Clark', 'Kent', 'clark.kent@dailyplanet.com', 'superman');

INSERT INTO t_fonctionnalite_fe (fe_id, fe_token, fe_type_acces) VALUES
(20, '*', 'BOTH'),
(21, 'feature_1', 'BOTH'),
(22, 'feature_2', 'BOTH'),
(23, 'feature_3', 'BOTH'),
(24, 'feature_4', 'BOTH'),
(25, 'feature_5', 'BOTH'),
(26, 'contacts', 'BOTH');

INSERT INTO t_structure_st (st_id, st_type, st_nom, st_email) VALUES
(30, 'ASSOCIATION', 'Mon association', 'email@association.com');

INSERT INTO tr_habilitation_ha (ut_id, fe_id, st_id) VALUES
(10, 21, 30),
(10, 22, 30),
(10, 23, 30),
(10, 26, 30),
(11, 21, 30),
(11, 22, 30);

INSERT INTO t_contact_co (co_id, co_prenom, co_nom, co_email) VALUES
(1, 'John', 'Doe', 'john.doe@unknown.com'),
(2, 'Albert', 'Enstein', 'albert.enstein@relativity.com'),
(3, 'Frank', 'Sinatra', 'frank.sinatra@american.com'),
(4, 'Leonardo', 'DiCaprio', 'leonardo.dicaprio@revenant.com'),
(5, 'Al', 'Pacino', 'al.pacino@scarface.com');

INSERT INTO t_fonction_fo (fo_id, fo_nom) VALUES
(1, 'Pratiquant(e)'),
(2, 'Président(e) de la fédération'),
(3, 'Président(e) de ligue régionale'),
(4, 'Président(e) de comité départemental'),
(5, 'Président(e) d''association'),
(6, 'Agent de développement'),
(7, 'Référent(e) des pratiques artistiques et culturelles'),
(8, 'Responsable de section');

INSERT INTO t_affectation_af (co_id, st_id, fo_id, af_statut) VALUES
(2, 30, 3, 'BENEVOLE');
