package com.bookStore.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.bookStore.entity.Product;

public class CSVHelper {

	public static String Type="text/csv";
	
	static String[] Headers= {"id", "productname", "productdescription", "productprice", "productstock"};
	
	public static boolean hasCSVFormat(MultipartFile file) {
		
		if(!Type.equals(file.getContentType())) {
			return false;
		}
		return true;
		
	}
	
	public static List<Product> csvToProducts(InputStream is){
		try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));
			CSVParser csvparser = new CSVParser(fileReader, 
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
			
						List<Product> products = new ArrayList<Product>();
						Iterable<CSVRecord> csvrecords = csvparser.getRecords(); 
						for(CSVRecord csvrecord :csvrecords) {
							Product product = new Product(
									Integer.parseInt(csvrecord.get("id")),
									csvrecord.get("productname"),
									csvrecord.get("productdescription"),
									Double.parseDouble(csvrecord.get("productprice")),
									Integer.parseInt(csvrecord.get("productstock")));
							products.add(product);		
					}
					return products;	
			
		}catch(Exception e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
		
		
		
	}
}
