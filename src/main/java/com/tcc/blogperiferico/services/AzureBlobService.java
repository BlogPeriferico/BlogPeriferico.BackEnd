package com.tcc.blogperiferico.services;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureBlobService {

    private final BlobContainerClient containerClient;

    public AzureBlobService(
            @Value("${azure.storage.connection-string}") String connectionString,
            @Value("${azure.storage.container-name}") String containerName) {

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        this.containerClient = serviceClient.getBlobContainerClient(containerName);
    }

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            BlobClient blobClient = containerClient.getBlobClient(fileName);

            try (InputStream inputStream = file.getInputStream()) {
                blobClient.upload(inputStream, file.getSize(), true);
            }

            return blobClient.getBlobUrl(); // URL pública ou SAS
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar arquivo para o Azure Blob", e);
        }
    }

    public void deleteFile(String fileName) {
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.delete();
    }
}