package com.bridgelabz.addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AdreessBookServiceTest {
    @Test
    public void givenAddressBookDatabseBWhenRetrivedShouldMatchPersonEntriesCount() {
        AddressBookService addressBookService = new AddressBookService();
        List<Person> addressBookDataList = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assert.assertEquals(3, addressBookDataList.size());
    }
    @Test
    public void givenContactNumberWhenUpdatedShouldSyncWithDatabase() {
        AddressBookService addressBookService = new AddressBookService();
        List<Person> addressBookData = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        addressBookService.updateContactNumber("Himanshu", "9424378087");
        boolean result = addressBookService.checkAddressBookInSyncWithDB("Himanshu");
        Assert.assertTrue(result);
    }
}
