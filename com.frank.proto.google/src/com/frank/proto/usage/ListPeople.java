package com.frank.proto.usage;

import java.io.FileInputStream;

import com.frank.proto.example.AddressBookProtos.AddressBook;
import com.frank.proto.example.AddressBookProtos.Person;

public class ListPeople {

	// Iterates though all people in the AddressBook and prints info about them.
	static void Print(AddressBook addressBook) {
		for (Person person : addressBook.getPeopleList()) {
			System.out.println("Person ID: " + person.getId());
			System.out.println("  Name: " + person.getName());

			if (person.getEmail().length() > 3) {
				System.out.println("  E-mail address: " + person.getEmail());
			}

			for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
				switch (phoneNumber.getType()) {
				case MOBILE:
					System.out.print("  Mobile phone #: ");
					break;
				case HOME:
					System.out.print("  Home phone #: ");
					break;
				case WORK:
					System.out.print("  Work phone #: ");
					break;
				}
				System.out.println(phoneNumber.getNumber());
			}
		}
	}

	// Main function: Reads the entire address book from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
//			System.exit(-1);
		}

		String filePath = "C:\\dev_frank\\com.frank.proto.tutorial\\src\\com\\frank\\proto\\usage\\addresses.data";

		// Read the existing address book.
//		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(args[0]));
		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(filePath));

		Print(addressBook);
	}
}
