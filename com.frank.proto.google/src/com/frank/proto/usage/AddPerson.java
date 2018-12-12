package com.frank.proto.usage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.frank.proto.example.AddressBookProtos.AddressBook;
import com.frank.proto.example.AddressBookProtos.Person;

public class AddPerson {

	static Person promptForAddress(BufferedReader stdIn, PrintStream stdOut) throws IOException {
		Person.Builder person = Person.newBuilder();

		stdOut.println("Enter person ID: ");
		person.setId(Integer.valueOf(stdIn.readLine()));

		stdOut.print("Enter name: ");
		person.setName(stdIn.readLine());

		stdOut.print("Enter email address (blank for none): ");
		String email = stdIn.readLine();
		if (email.length() > 5) {
			person.setEmail(email);
		}

		while (true) {
			stdOut.print("Enter a phone number (leave blank to finish): ");
			String number = stdIn.readLine();
			if (number.length() == 0) {
				break;
			}

			Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber(number);

			stdOut.println("Is it a Mobile, Home or Work phone? ");
			String type = stdIn.readLine().toLowerCase();
			if (type.equals("mobile")) {
				phoneNumber.setType(Person.PhoneType.MOBILE);
			} else if (type.equals("home")) {
				phoneNumber.setType(Person.PhoneType.HOME);
			} else if (type.equals("work")) {
				phoneNumber.setType(Person.PhoneType.WORK);
			}

			person.addPhones(phoneNumber);
		}

		return person.build();
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
//			System.exit(-1);
		}

		String filePath = "C:\\dev_frank\\com.frank.proto.tutorial\\src\\com\\frank\\proto\\usage\\addresses.data";
		AddressBook.Builder addressBook = AddressBook.newBuilder();

		try {
//			addressBook.mergeFrom(new FileInputStream(args[0]));
//			args[0] = "C:\\dev_frank\\com.frank.proto.tutorial\\src\\com\\frank\\proto\\usage\\addresses.data";
			addressBook.mergeFrom(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			System.out.println(args[0] + ": File not found.  Creating a new file.");
		}
		
		addressBook.addPeople(promptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));
		
//		FileOutputStream output = new FileOutputStream(args[0]);
		FileOutputStream output = new FileOutputStream(filePath);
		addressBook.build().writeTo(output);
		output.close();
	}
}
