package com.example.demo;

//CustomerService.java

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import dto.AuthRequest;
import dto.AuthResponse;
import dto.Customer;

import java.util.Arrays;
import java.util.List;

@Service
public interface CustomerService {
    List<Customer> getCustomerList(String token);

 public static final String apiBaseUrl = "";

 public static final RestTemplate restTemplate = new RestTemplate();


 public default String authenticateUser(String loginId, String password) {
     String authApiUrl = apiBaseUrl + "/assignment_auth.jsp";

     HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.APPLICATION_JSON);

     HttpEntity<Object> requestEntity = new HttpEntity<>(new AuthRequest(loginId, password), headers);

     try {
         ResponseEntity<AuthResponse> responseEntity = restTemplate.exchange(authApiUrl, HttpMethod.POST, requestEntity, AuthResponse.class);
         AuthResponse authResponse = responseEntity.getBody();
         return authResponse.getToken();
     } catch (HttpStatusCodeException e) {
         throw new RuntimeException("Authentication failed. Invalid credentials.");
     }
 }

 public default ResponseEntity<String> createCustomer(Customer customer, String bearerToken) {
     String createCustomerApiUrl = apiBaseUrl + "/assignment.jsp?cmd=create";

     HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.APPLICATION_JSON);
     headers.setBearerAuth(bearerToken);

     HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

     return restTemplate.exchange(createCustomerApiUrl, HttpMethod.POST, requestEntity, String.class);
 }

 public default List<Customer> getCustomerList1(String bearerToken) {
     String getCustomerListApiUrl = apiBaseUrl + "/assignment.jsp?cmd=get_customer_list";

     HttpHeaders headers = new HttpHeaders();
     headers.setBearerAuth(bearerToken);

     HttpEntity<?> requestEntity = new HttpEntity<>(headers);

     ResponseEntity<Customer[]> responseEntity = restTemplate.exchange(getCustomerListApiUrl, HttpMethod.GET, requestEntity, Customer[].class);
     return Arrays.asList(responseEntity.getBody());
 }

 public default ResponseEntity<String> deleteCustomer(String uuid, String bearerToken) {
     String deleteCustomerApiUrl = apiBaseUrl + "/assignment.jsp?cmd=delete&uuid=" + uuid;

     HttpHeaders headers = new HttpHeaders();
     headers.setBearerAuth(bearerToken);

     HttpEntity<?> requestEntity = new HttpEntity<>(headers);

     return restTemplate.exchange(deleteCustomerApiUrl, HttpMethod.POST, requestEntity, String.class);
 }

 public default ResponseEntity<String> updateCustomer(Customer customer, String bearerToken) {
     String updateCustomerApiUrl = apiBaseUrl + "/assignment.jsp?cmd=update&uuid=" + customer.getUuid();

     HttpHeaders headers = new HttpHeaders();
     headers.setContentType(MediaType.APPLICATION_JSON);
     headers.setBearerAuth(bearerToken);

     HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

     return restTemplate.exchange(updateCustomerApiUrl, HttpMethod.POST, requestEntity, String.class);
 }

Customer getCustomerDetails(String uuid, String token);
}
