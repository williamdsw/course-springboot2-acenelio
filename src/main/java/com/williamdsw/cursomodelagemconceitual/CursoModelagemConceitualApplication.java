package com.williamdsw.cursomodelagemconceitual;

import com.williamdsw.cursomodelagemconceitual.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoModelagemConceitualApplication implements CommandLineRunner
{
    @Autowired
    private S3Service s3service;
    
    public static void main (String[] args)
    {
        SpringApplication.run (CursoModelagemConceitualApplication.class, args);
    }

    @Override
    public void run (String... args) throws Exception
    {
       s3service.uploadFile ("C:\\file.JPG");
    }
}