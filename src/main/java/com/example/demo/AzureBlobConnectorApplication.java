package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

@SpringBootApplication
public class AzureBlobConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureBlobConnectorApplication.class, args);
		connectToStorage();
	}

	public static void connectToStorage() {
	    String connectStr = System.getenv("AZURE_STORAGE_CONNECTION_STRING"); 
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
		BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("Container_name");
		
		// List the blob(s) in the container.
		for (BlobItem blobItem : containerClient.listBlobs()) {
		    System.out.println("\t" + blobItem.getName());
		}
	}

}
