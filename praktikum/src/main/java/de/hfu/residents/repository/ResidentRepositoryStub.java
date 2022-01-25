package de.hfu.residents.repository;

import de.hfu.residents.domain.Resident;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResidentRepositoryStub implements ResidentRepository{

    List<Resident> residentUser = new ArrayList<>();

    public void addUser(Resident a){
        residentUser.add(a);
    }

    @Override
    public List<Resident> getResidents() {
        return residentUser;
    }
}
