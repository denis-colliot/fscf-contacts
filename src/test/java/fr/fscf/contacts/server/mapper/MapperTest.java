package fr.fscf.contacts.server.mapper;

import fr.fscf.contacts.server.inject.GuiceJUnitRunner;
import fr.fscf.contacts.server.inject.MapperTestModule;
import fr.fscf.contacts.server.inject.UseModules;
import fr.fscf.contacts.server.model.*;
import fr.fscf.contacts.server.model.referential.StructureType;
import fr.fscf.contacts.shared.dto.StructureDTO;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Mapper unit tests.
 */
@RunWith(GuiceJUnitRunner.class)
@UseModules(MapperTestModule.class)
public class MapperTest {

    @Inject
    private BeanMapper beanMapper;

    @Test(expected = IllegalArgumentException.class)
    public void testStructureClassMappingKo() {
        assertThat(Structure.getTypeClass(null)).isEqualTo(Federation.class);
    }

    @Test
    public void testStructureClassMappingOk() {
        assertThat(Structure.getTypeClass(StructureType.FEDERATION)).isEqualTo(Federation.class);
        assertThat(Structure.getTypeClass(StructureType.COMITE_REGIONAL)).isEqualTo(RegionalCommittee.class);
        assertThat(Structure.getTypeClass(StructureType.COMITE_DEPARTEMENTAL)).isEqualTo(DepartmentalCommittee.class);
        assertThat(Structure.getTypeClass(StructureType.ASSOCIATION)).isEqualTo(Association.class);
    }

    @Test
    public void testStructureTypeMapping() {
        StructureDTO dto = beanMapper.map(new Federation(), StructureDTO.class);
        assertThat(dto.getType()).isEqualTo(StructureType.FEDERATION);

        dto = beanMapper.map(new Association(), StructureDTO.class);
        assertThat(dto.getType()).isEqualTo(StructureType.ASSOCIATION);
    }

}
