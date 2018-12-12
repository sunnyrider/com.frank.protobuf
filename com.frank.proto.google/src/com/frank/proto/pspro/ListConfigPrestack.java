package com.frank.proto.pspro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import com.frank.proto.pspro.ConfigPrestackPro.ConfigStruct;
import com.frank.proto.pspro.ConfigPrestackPro.GeoModelStruct;
import com.frank.proto.pspro.ConfigPrestackPro.GeoModelStruct.DataInlineHigherXlineLowXY;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.XmlFormat;

import jdk.nashorn.internal.runtime.PrototypeObject;

public class ListConfigPrestack {

	// Iterates though all people in the AddressBook and prints info about them.
	static void Print(ConfigStruct config) {

		System.out.println("PR Name: " + config.getPrName());
		System.out.println("User Comments: " + config.getUserComments());

		for (GeoModelStruct geoStruct : config.getGeoModelStructList()) {
			System.out.println("Corner3Set: " + geoStruct.getCorner3Set());
			System.out.println("NInlines: " + geoStruct.getNInlines());
			System.out.println("NXlines: " + geoStruct.getNXlines());
			System.out.println("BetweenInlines: " + geoStruct.getDBetweenInlines());
			System.out.println("BetweenXlines: " + geoStruct.getDBetweenXlines());
			System.out.println("OneInlineNum: " + geoStruct.getOneInlineNum());
			System.out.println("OneXlineNum: " + geoStruct.getOneXlineNum());
			System.out.println("XOne: " + geoStruct.getXOne());
			System.out.println("YOne: " + geoStruct.getYOne());
			System.out.println("AngleRotDeg: " + geoStruct.getAngleRotDeg());
			System.out.println("Xlinedir: " + geoStruct.getXlinedir());
			System.out.println("FirstInlineNum: " + geoStruct.getFirstInlineNum());
			System.out.println("FirstXlineNum: " + geoStruct.getFirstXlineNum());
			System.out.println("Mode: " + geoStruct.getMode());
			System.out.println("Is2DLinesProject: " + geoStruct.getIs2DLinesProject());
			System.out.println("CRS: " + geoStruct.getCRS());
			System.out.println("MetricCRS: " + geoStruct.getMetricCRS());

			for (GeoModelStruct.DataInlineLowXlineLowXY data : geoStruct.getLowXlineLowXYList()) {
				System.out.println("InlineLowXlineLowXy: " + data.getInlineLowXlineLowXy());
			}
			for (GeoModelStruct.DataInlineLowXlineHigherXY data : geoStruct.getLowXlineHigherXYList()) {
				System.out.println("InlineLowXlineHigherXy: " + data.getInlineLowXlineHigherXy());
			}
			for (DataInlineHigherXlineLowXY data : geoStruct.getHigherXlineLowXYList()) {
				System.out.println("InlineHigherXlineLowXy: " + data.getInlineHigherXlineLowXy());
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

//		String filePath = "C:\\dev_frank\\com.frank.proto.tutorial\\src\\com\\frank\\proto\\usage\\ConfigStruct.xml";
		String filePath = "C:\\dev_prestackconf\\com.frank.xml.faster\\data\\GeoModelStruct.xml";

		String fileContent = readFileAsString(filePath);

		Message.Builder builder = GeoModelStruct.newBuilder();
		FileInputStream stream = new FileInputStream(filePath);

		XmlFormat xmlFormat = new XmlFormat();
//		String result = xmlFormat.printToString(stream.read());
		xmlFormat.merge(stream, builder);


		// Read the existing address book.
//		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(args[0]));
		ConfigStruct configStruct = ConfigStruct.parseFrom(new FileInputStream(filePath));

		Print(configStruct);
	}

	private static String readFileAsString(String filePath) throws IOException
    {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1)
        {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}
