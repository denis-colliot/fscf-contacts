INSERT INTO t_utilisateur_ut (ut_id, ut_nom, ut_prenom, ut_email, ut_password, ut_actif) VALUES
(1, 'Colliot', 'Denis', 'denis.colliot@gmail.com', '$2a$10$sZ0Xr5EaDM6JWpnkis1bDuLJOop6vpaMolCvOYJhEpPcidu0tkXp6', true),
(2, 'Bouvet', 'Sébastien', 'bouvet.sebastien@gmail.com', '$2a$10$sZ0Xr5EaDM6JWpnkis1bDuLJOop6vpaMolCvOYJhEpPcidu0tkXp6', true);

INSERT INTO t_fonctionnalite_fe (fe_id, fe_token, fe_type_acces) VALUES
(100, '*', 'BOTH'),
(101, 'contacts', 'AUTHENTICATED_ONLY'),
(102, 'contact', 'AUTHENTICATED_ONLY'),
(103, 'associations', 'AUTHENTICATED_ONLY'),
(104, 'association', 'AUTHENTICATED_ONLY'),
(105, 'users', 'AUTHENTICATED_ONLY'),
(106, 'user', 'AUTHENTICATED_ONLY');

INSERT INTO t_structure_st (st_id, st_type, st_nom) VALUES
(1000, 'FEDERATION', 'Fédération nationale');

INSERT INTO tr_habilitation_ha (ut_id, fe_id, st_id) VALUES
(1, 101, 1000),
(1, 102, 1000),
(1, 103, 1000),
(1, 104, 1000),
(1, 105, 1000),
(1, 106, 1000),
(2, 101, 1000),
(2, 102, 1000),
(3, 103, 1000),
(4, 104, 1000),
(5, 105, 1000),
(6, 106, 1000);