package de.hfu.residents.service;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepositoryStub;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ResidentServiceTest {

    ResidentRepositoryStub residentRepositoryStub = new ResidentRepositoryStub();

    Resident Johannes = new Resident("Johannes", "Zoeller","Hofacker" , "Freudenstadt", new Date(2000,26,8));
    Resident Tim = new Resident("Tim", "Nimmerzahl","Alte Strasse 500" , "Laputa", new Date(1000,5,3));
    Resident Erich = new Resident("Erich", "Zann","Namenlos" , "Ryley", new Date(10,1,1994));

    @Test
    public void getUniqueResident() {

        BaseResidentService baseResidentService = new BaseResidentService();
        residentRepositoryStub.addUser(Johannes);
        residentRepositoryStub.addUser(Tim);
        residentRepositoryStub.addUser(Erich);
        Resident nicht = new Resident("X", "Y","Alesia" , "Timbuktu", new Date(10,1,100));
        baseResidentService.setResidentRepository(residentRepositoryStub);


        try
        {
            assertEquals(Johannes, baseResidentService.getUniqueResident(Johannes));
            assertEquals(Tim, baseResidentService.getUniqueResident(Tim));
            assertEquals(Erich, baseResidentService.getUniqueResident(Erich));
        }
        catch (ResidentServiceException e) {
            System.out.println("Oh oh");
        }

        try{
            baseResidentService.getUniqueResident(nicht);
        }
        catch (ResidentServiceException e) {
            System.out.println("Komm geb was aus");
        }
    }

    @Test
    public void getFilteredResidentsList() {

        BaseResidentService baseResidentService = new BaseResidentService();
        residentRepositoryStub.addUser(Johannes);
        residentRepositoryStub.addUser(Tim);
        residentRepositoryStub.addUser(Erich);
        Resident suchMatixStraßeFamilyName = new Resident("", "D*","In*" , "", null);
        Resident suchMatixStraße = new Resident("", "","In*" , "", null);
        Resident suchMatixFamilyName = new Resident("", "D*","" , "", null);
        Resident suchMatixGivenName = new Resident("M*", "","" , "", null);
        Resident suchMatixNotFound = new Resident("Z*", "","In*" , "", null);
        Resident suchMatixLimit = new Resident("*", "*","*" , "*", null);
        baseResidentService.setResidentRepository(residentRepositoryStub);

        List<Resident> list = baseResidentService.getFilteredResidentsList(suchMatixStraße);
        assertEquals(Tim.getFamilyName(),list.get(0).getFamilyName());
        assertEquals(Erich.getFamilyName(),list.get(1).getFamilyName());
        list = baseResidentService.getFilteredResidentsList(suchMatixNotFound);
        assertEquals(0,list.size());
        list = baseResidentService.getFilteredResidentsList(suchMatixStraßeFamilyName);
        assertEquals(Tim.getFamilyName(),list.get(0).getFamilyName());
        list = baseResidentService.getFilteredResidentsList(suchMatixGivenName);
        assertEquals(Johannes.getGivenName(),list.get(0).getGivenName());
        list = baseResidentService.getFilteredResidentsList(suchMatixFamilyName);
        assertEquals(Tim.getGivenName(),list.get(0).getGivenName());

        list = baseResidentService.getFilteredResidentsList(suchMatixLimit);
        assertEquals(Johannes.getFamilyName(),list.get(0).getFamilyName());
        assertEquals(Tim.getFamilyName(),list.get(1).getFamilyName());
        assertEquals(Erich.getFamilyName(),list.get(2).getFamilyName());
    }
}