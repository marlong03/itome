package com.sistema.itome.perosnas;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileServices{
	
	@Autowired
	PersonasRepository repository;
	
	// Store File Data to Database
	public void store(MultipartFile file){
		try {
			List<Personas> lstCustomers = ExcelUtils.parseExcelFile(file.getInputStream());
    		// Save Customers to DataBase
			repository.saveAll(lstCustomers);
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
	
	// Load Data to Excel File
    public ByteArrayInputStream loadFile() {
    	List<Personas> customers = (List<Personas>) repository.findAll();
    	
    	try {
    		ByteArrayInputStream in = ExcelUtils.customersToExcel(customers);
    		return in;
		} catch (IOException e) {}
    	
        return null;
    }
}
