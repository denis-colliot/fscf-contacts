select
		co.*
	from
		t_contact_co as co
		join t_affectation_af as af on af.co_id = co.co_id
		join t_structure_st as fed on af.st_id = fed.st_id
		join t_habilitation_ha as ha on ha.st_id = reg_com.st_id
		join t_fonctionnalite_fe as fe on fe.fe_id = ha.fe_id
	where
		ha.ut_id = 1 and fe.fe_token = 'contacts';


-- Gets the complete tree structures.
WITH RECURSIVE structures_tree AS (
  SELECT structure.*, 1::INT AS depth, structure.st_id::TEXT AS path
  FROM t_structure_st AS structure
  WHERE structure.parent_id IS NULL
UNION ALL
  SELECT c.*, p.depth + 1 AS depth, (p.path || '->' || c.st_id::TEXT)
  FROM structures_tree AS p, t_structure_st AS c
  WHERE c.parent_id = p.st_id
)
SELECT * FROM structures_tree AS n ORDER BY n.st_id ASC;

-- WITH HABILITATIONS
WITH RECURSIVE structures_tree AS (
  SELECT structure.*, 1::INT AS depth, structure.st_id::TEXT AS path
  FROM
    t_structure_st AS structure
    join t_habilitation_ha as ha on ha.st_id = structure.st_id
    join t_fonctionnalite_fe as fe on fe.fe_id = ha.fe_id
  WHERE
    ha.ut_id = 1 and fe.fe_token = 'contacts'
UNION ALL
  SELECT c.*, p.depth + 1 AS depth, (p.path || '->' || c.st_id::TEXT)
  FROM structures_tree AS p, t_structure_st AS c
  WHERE c.parent_id = p.st_id
)
SELECT
  *
FROM
  structures_tree as st


-- WITH HABILITATIONS > CONTACTS
WITH RECURSIVE structures_tree(st_id) AS (
  SELECT
    root_structure.st_id
  FROM
    t_structure_st AS root_structure
    join t_habilitation_ha as ha on ha.st_id = root_structure.st_id
    join t_fonctionnalite_fe as fe on fe.fe_id = ha.fe_id
  WHERE
    ha.ut_id = 1 and fe.fe_token = 'contacts'
UNION ALL
  SELECT
    child_structure.st_id
  FROM
    structures_tree AS parent_structure,
    t_structure_st AS child_structure
  WHERE
    child_structure.parent_id = parent_structure.st_id
)
SELECT
  co.*, st.st_name
FROM
  t_contact_co as co
  join t_affectation_af as af on af.co_id = co.co_id
  join structures_tree as st on af.st_id = st.st_id
--WHERE co.co_id = 22
