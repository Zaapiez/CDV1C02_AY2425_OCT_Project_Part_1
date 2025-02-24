package com.assignment;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddProductServletTest {

    @InjectMocks
    private AddProductServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    private StringWriter responseWriter;
    private PrintWriter writer;

    @BeforeEach
    void setUp() throws Exception {
        responseWriter = new StringWriter();
        writer = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void testDoPost_Success() throws Exception {
        when(request.getParameter("prodname")).thenReturn("Blastoise");
        when(request.getParameter("prodquantity")).thenReturn("5");

        servlet.doPost(request, response);

        writer.flush();
        assertTrue(responseWriter.toString().contains("Served at:"));
    }

  //  @Test
  //  void testDoPost_MissingParameters() throws Exception {
 //       when(request.getParameter("prodname")).thenReturn(null);
 //       when(request.getParameter("prodquantity")).thenReturn("5");

 //       servlet.doPost(request, response);

//        writer.flush();
  //      assertFalse(responseWriter.toString().isEmpty()); 
//    }

    @Test
    void testDoGet() throws Exception {
        servlet.doGet(request, response);
        writer.flush();
        assertTrue(responseWriter.toString().contains("Served at:"));
    }
}