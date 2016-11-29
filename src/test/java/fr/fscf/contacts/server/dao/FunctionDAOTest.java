package fr.fscf.contacts.server.dao;

import org.junit.Test;

import javax.inject.Inject;

import static fr.fscf.contacts.server.inject.TestDatabaseInitialization.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FunctionDAOTest extends AbstractDAOTest {

    @Inject
    private FunctionDAO functionDAO;

    @Test
    public void existToken() {
        assertThat(functionDAO.findAll()).containsExactly(
                developmentAgent,
                pratiquant,
                assocation_president,
                departemental_committee_president,
                federation_president,
                regional_league_president,
                section_responsible,
                practice_referent
        );
    }

}
