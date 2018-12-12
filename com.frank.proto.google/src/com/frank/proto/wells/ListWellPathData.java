package com.frank.proto.wells;

import java.io.FileInputStream;

import com.frank.proto.wells.PSProVolumes.WellPathData;
import com.frank.proto.wells.PSProVolumes.WellPathPosition;

public class ListWellPathData {

	// Iterates though all people in the AddressBook and prints info about them.
	static void Print(WellPathData wellPathData) {
		for (WellPathPosition pos : wellPathData.getWellPathList()) {
			System.out.println("Measured Depth: " + pos.getMeasuredDepth());
			System.out.println("  Inline Pos: " + pos.getInlinePos());
			System.out.println("  Xline Pos: " + pos.getXlinePos());
			System.out.println("  True Vert. Depth: " + pos.getTrueVerticalDepth());			
		}
	}

	// Main function: Reads the entire address book from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
//			System.exit(-1);
		}

		String filePath = "C:\\dev_frank\\com.frank.proto.tutorial\\src\\com\\frank\\proto\\wells\\wellpath.wpd";

		// Read the existing address book.
		WellPathData wellPathData = WellPathData.parseFrom(new FileInputStream(filePath));

		Print(wellPathData);
	}
}
