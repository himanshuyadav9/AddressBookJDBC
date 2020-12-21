package com.bridgelabz.addressbook;

import java.time.LocalDate;
import java.util.List;
import java.util.prefs.Preferences;

public class AddressBookService {
    public enum IOService {DB_IO, REST_IO}

    private List<Person> personList;

    private AddressBookDatabase addressBookDatabase;

    public AddressBookService () {
        addressBookDatabase = AddressBookDatabase.getInstance();
    }

    public AddressBookService(List<Person> personList) {
        this();
        this.personList = personList;
    }

    public List<Person> readAddressBookData(IOService ioService) {
        if (ioService.equals(IOService.DB_IO))
            this.personList = addressBookDatabase.readData();
        return personList;
    }
    public void updateContactNumber(String firstName, String phoneNumber) {
        int result = addressBookDatabase.updateContactNumber(firstName, phoneNumber);
        if (result == 0) return;
        Person person = this.getPersonData(firstName);
        if (person != null) person.phoneNumber = phoneNumber;
    }

    public boolean checkAddressBookInSyncWithDB(String firstName) {
        List<Person> personList = addressBookDatabase.getaddressBookData(firstName);
        return personList.get(0).equals(addressBookDatabase.getaddressBookData(firstName));
    }

    private Person getPersonData(String firstName) {
        return this.personList.stream()
                .filter(personDataItem -> personDataItem.firstName.equals(firstName))
                .findFirst()
                .orElse(null);
    }
    public List<Person> readAddressBookForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate) {
        if (ioService.equals(IOService.DB_IO))
            return addressBookDatabase.getAddressBookForDateRange(startDate, endDate);
        return null;
    }

    public static void main(String[] args){
        System.out.println("Welcome to Address Book Service Database");

    }
}
