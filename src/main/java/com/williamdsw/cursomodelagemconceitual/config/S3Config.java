package com.williamdsw.cursomodelagemconceitual.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author William
 */

@Configuration
public class S3Config
{
    // ------------------------------------------------------------------------------------//
    // CAMPOS
    
    @Value ("${aws.access_key_id}")
    private String accessKey;
    
    @Value ("${aws.secret_access_key}")
    private String secretKey;
    
    @Value ("${s3.region}")
    private String regionName;
    
    // ------------------------------------------------------------------------------------//
    // BEANS
    
    @Bean
    public AmazonS3 buildAmazonClient ()
    {
        BasicAWSCredentials credentials = new BasicAWSCredentials (accessKey, secretKey);
        AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider (credentials);
        AmazonS3 client = AmazonS3ClientBuilder.standard ()
                          .withRegion (Regions.fromName (regionName))
                          .withCredentials (provider).build ();
        return client;
    }
}