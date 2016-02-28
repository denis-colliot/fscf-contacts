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
(25, 'feature_5', 'BOTH');

INSERT INTO t_structure_st (st_id, st_type, st_nom) VALUES
(30, 'ASSOCIATION', 'Mon association');

INSERT INTO tr_habilitation_ha (ut_id, fe_id, st_id) VALUES
(10, 21, 30),
(10, 22, 30),
(10, 23, 30),
(11, 21, 30),
(11, 22, 30);