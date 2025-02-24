package com.assignment;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServletTest {

    @InjectMocks
    private inventoryservlet servlet;  // The servlet being tested

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    void testShowEditForm_ProductFound() throws SQLException, ServletException, IOException {
       
        // Your actual test logic
        servlet.showEditForm(request, response);

        // Verifications
        verify(request).setAttribute(eq("product"), any(product.class));
        verify(dispatcher).forward(request, response);
    }


  //  @Test
    // void testShowEditForm_ProductNotFound() throws SQLException, ServletException, IOException {
//   String nonExistentProduct = "NonExistentProduct"; // Store the product name

  //    when(request.getParameter("prodname")).thenReturn(nonExistentProduct);
  //      when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    //   when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
     //  when(mockResultSet.next()).thenReturn(false);

  //    servlet.showEditForm(request, response);

        // More specific verification: check the redirect URL
   //   verify(response).sendRedirect("http://localhost:8090/Assignmentpt2/inventoryservlet/dashboard");

        
     //  verify(request, never()).setAttribute(anyString(), any()); 
    //}

    @Test
    void testListProduct() throws SQLException, ServletException, IOException {

        // Invoke the method
        servlet.listProduct(request, response);

        // Verify products are set in request attribute
        verify(request).setAttribute(eq("listProduct"), any(List.class));
        verify(dispatcher).forward(request, response);
    }

}