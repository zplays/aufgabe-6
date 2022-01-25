package de.hfu.residents.service;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.easymock.EasyMock.*;

public class ResidentServiceMockTest {

    BaseResidentService baseResidentService = new BaseResidentService();
    List<Resident> list = new ArrayList<Resident>();


    Resident Johannes = new Resident("Johannes", "Zoeller","Hofacker" , "Freudenstadt", new Date(2000,26,8));
    Resident Tim = new Resident("Tim", "Nimmerzahl","Alte Strasse 500" , "Laputa", new Date(1000,5,3));
    Resident Erich = new Resident("Erich", "Zann","Namenlos" , "Ryley", new Date(10,1,1994));

    @Test
    public void getUniqueResident() throws ResidentServiceException {
        ResidentRepository residentRepository = createMock(ResidentRepository.class);
        list.add(Johannes);
        list.add(Tim);
        list.add(Erich);

        expect(residentRepository.getResidents()).andReturn(list).times(3);
        baseResidentService.setResidentRepository(residentRepository);
        replay(residentRepository);

        Resident johannes = baseResidentService.getUniqueResident(Johannes);
        Resident JohannesTest = new Resident("Johannes", "Zoeller","Hofacker" , "Freudenstadt", new Date(2000,26,8));
        assertEquals(johannes.getFamilyName(),JohannesTest.getFamilyName());

        Resident tim = baseResidentService.getUniqueResident(Tim);
        Resident TimTest = new Resident("Tim", "Nimmerzahl","Alte Strasse 500" , "Laputa", new Date(1000,5,3));
        assertEquals(tim.getStreet(),TimTest.getStreet());

        Resident erich = baseResidentService.getUniqueResident(Erich);
        Resident ErichTest = new Resident("Erich", "Zann","Namenlos" , "Ryley", new Date(10,1,1994));
        assertEquals(erich.getStreet(),ErichTest.getStreet());

        verify(residentRepository);

    }

    @Test
    public void getFilteredResidentsList() {

        ResidentRepository residentRepository = createMock(ResidentRepository.class);
        list.add(Johannes);
        list.add(Tim);
        list.add(Erich);

        expect(residentRepository.getResidents()).andReturn(list).times(5);
        baseResidentService.setResidentRepository(residentRepository);
        replay(residentRepository);


        Resident suchMatixStraßeFamilyName = new Resident("", "D*","In*" , "", null);
        Resident suchMatixStraße = new Resident("", "","In*" , "", null);
        Resident suchMatixFamilyName = new Resident("", "D*","" , "", null);
        Resident suchMatixGivenName = new Resident("M*", "","" , "", null);
        Resident suchMatixNotFound = new Resident("Z*", "","In*" , "", null);
        Resident suchMatixLimit = new Resident("*", "*","*" , "*", null);

        List<Resident> list = baseResidentService.getFilteredResidentsList(suchMatixFamilyName);
        assertThat(list.get(0).getFamilyName(), is(Tim.getFamilyName()));

        list = baseResidentService.getFilteredResidentsList(suchMatixStraßeFamilyName);
        assertThat(list.get(0).getFamilyName(), is(Tim.getFamilyName()));

        list = baseResidentService.getFilteredResidentsList(suchMatixStraße);
        assertThat(list.get(0).getFamilyName(),is(Tim.getFamilyName()));
        assertThat(list.get(1).getFamilyName(),is(Erich.getFamilyName()));

        list = baseResidentService.getFilteredResidentsList(suchMatixGivenName);
        assertThat(list.get(0).getGivenName(),is(Johannes.getGivenName()));

        list = baseResidentService.getFilteredResidentsList(suchMatixNotFound);
        assertThat( list.size(), is(0));

        verify(residentRepository);
    }
}