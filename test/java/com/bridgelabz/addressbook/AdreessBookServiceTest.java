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
}
