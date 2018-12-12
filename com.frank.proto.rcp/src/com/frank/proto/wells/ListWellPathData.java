package com.frank.proto.wells;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.frank.proto.wells.PSProVolumes.WellHorizonIntersection;
import com.frank.proto.wells.PSProVolumes.WellPathData;
import com.frank.proto.wells.PSProVolumes.WellPathPosition;
import com.frank.proto.wells.PSProVolumes.WellTop;

public class ListWellPathData {

	// Iterates though all people in the AddressBook and prints info about them.
	static void Print(WellPathData wellPathData, FileOutputStream out) {
		System.out.println(System.getProperty("user.home"));
		StringBuffer buffer = new StringBuffer();

		for (WellTop wellTop : wellPathData.getWellTopsList()) {
			System.out.println("WellTop Name:: " + wellTop.getName());
			buffer.append("WellTop Name:: " + wellTop.getName());

			System.out.println("WellTop MD: " + wellTop.getMd());
			buffer.append("\n    MD: " + wellTop.getMd());

			System.out.println();
			buffer.append("\n");
		}

		System.out.println("\n*****     *****     *****     *****\n");
		buffer.append("\n*****     *****     *****     *****\n\n");

		for (WellPathPosition pos : wellPathData.getWellPathList()) {
			System.out.println("WellPathPosition, Measured Depth: " + pos.getMeasuredDepth());
			buffer.append("WellPathPosition, Measured Depth: " + pos.getMeasuredDepth());

			System.out.println("    Inline Pos: " + pos.getInlinePos());
			buffer.append("\n    Inline Pos: " + pos.getInlinePos());

			System.out.println("\n    Xline Pos: " + pos.getXlinePos());
			buffer.append("\n    Xline Pos: " + pos.getXlinePos());

			System.out.println("    True Vert. Depth: " + pos.getTrueVerticalDepth());
			buffer.append("\n    True Vert. Depth: " + pos.getTrueVerticalDepth());

			System.out.println();
			buffer.append("\n");
		}

		for (WellHorizonIntersection intersection : wellPathData.getHorizonIntersectionsList()) {
			System.out.println("WellHorizonIntersection, Horizontal BFID: " + intersection.getHorizonBfid());
			System.out.println("WellHorizonIntersection, Intersect Pos: " + intersection.getIntersectPosition());
		}

		byte[] contentInBytes = buffer.toString().getBytes();
		try {
			out.write(contentInBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Main function: Reads the entire address book from a file and prints all
	// the information inside.
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
//			System.exit(-1);
		}

		FileDialog dialog = new FileDialog(new Shell());
		String filePath = dialog.open();

		String outPath = "C:\\dev_frank\\com.frank.proto.tutorial\\src\\com\\frank\\proto\\wells\\wellResults.data";
		File data = new File(outPath);
		if (!data.exists()) {
			data.createNewFile();
		}

		// Read the existing address book.
		WellPathData wellPathData = WellPathData.parseFrom(new FileInputStream(filePath));

		FileOutputStream output = new FileOutputStream(data);
		Print(wellPathData, output);
		output.close();
	}
}
