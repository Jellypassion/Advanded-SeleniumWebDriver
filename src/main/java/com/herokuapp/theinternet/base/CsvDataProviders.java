package com.herokuapp.theinternet.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvDataProviders {
	
	@DataProvider(name = "csvReader")
	public static Iterator<Object[]> csvReader(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
		//method is used to create filepath from the classname, because classname is the same(and now it has to be the same) 
		//as foldername + .csv file with data provider
		String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
				+ "dataproviders" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
				+ method.getName() + ".csv";
		File file = new File(pathname);
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			//the array with keys (in header of the file )
			String[] keys = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				//в переменную dataParts поочередно присваиваются строки файла (начиная со второй)
				while ((dataParts = reader.readNext()) != null) {
					//Построчно создаем Map и добавляем в него значения key-value ("No":"1"; "Username":"incorrectUsername"...)
					Map<String, String> testData = new HashMap<String, String>();
					for (int i = 0; i < keys.length; i++) {
						testData.put(keys[i], dataParts[i]);
					}
					//в list добавляем каждую строку в виде масива обьектов
					list.add(new Object[] { testData });
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathname + " was not found.\n" + e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathname + " file.\n" + e.getStackTrace().toString());
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list.iterator();
	}
	
}
