package com.example.BusTicketBookingApplication;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.controller.AdminController;
import com.example.dto.AdminDTO;
import com.example.dto.AdminLoginDTO;
import com.example.service.Adminservice;
import com.example.service.BookingService;
import com.example.service.BusService;
import com.example.service.RatingService;
import com.example.service.TripService;
import com.example.service.UserService;

public class AdminControllerTest {

    @Mock
    private Adminservice adminService;

    @Mock
    private UserService userService;

    @Mock
    private BusService busService;

    @Mock
    private TripService tripService;

    @Mock
    private BookingService bookingService;

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

   
    @Test
    public void testAddAdmin() {
        AdminDTO adminDTO = new AdminDTO();

        ResponseEntity<String> responseEntity = adminController.addAdmin(adminDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo("Admin register Sucessfully");

        verify(adminService, times(1)).registerAdmin(adminDTO);
    }

    @Test
    public void testLoginAdmin() {
        AdminLoginDTO adminLoginDTO = new AdminLoginDTO();

        ResponseEntity<String> responseEntity = adminController.loginAdmin(adminLoginDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Admin LogIn Sucessfully");

        verify(adminService, times(1)).login(adminLoginDTO);
    }

    @Test
    public void testDeleteAdminById() {
        long adminId = 1L;

        ResponseEntity<String> responseEntity = adminController.deleteAdminById(adminId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getBody()).isEqualTo("Admin Profile Deleted successfully");

        verify(adminService, times(1)).deleteAdmin(adminId);
    }

    @Test
    public void testUpdateAdmin() {
        long adminId = 1L;
        AdminDTO updatedAdmin = new AdminDTO();

        ResponseEntity<String> responseEntity = adminController.updateAdmin(adminId, updatedAdmin);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(responseEntity.getBody()).isEqualTo("Admin Profile Updated successfully");

        verify(adminService, times(1)).updateAdmin(adminId, updatedAdmin);
    }
}

    