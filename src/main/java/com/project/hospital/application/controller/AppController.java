package com.project.hospital.application.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.hospital.application.details.CustomUserDetails;
import com.project.hospital.application.model.Doctor;
import com.project.hospital.application.model.User;
import com.project.hospital.application.model.appointment;
import com.project.hospital.application.repository.UserRepository;
import com.project.hospital.application.service.HospitalAppointmentService;
import com.project.hospital.application.service.HospitalService;
import com.project.hospital.application.service.HospitalService;

@Controller
public class AppController {
	
	@Autowired
	//private MovieService service;
	private HospitalService service;
	@Autowired
	private HospitalAppointmentService appointmentservice;
	//private Movieappointmentservice appointmentservice;
	
//	 @Autowired
//	    private AppointmentService appointmentService1;

//	    // Show the appointment form
//	    @GetMapping("/appointment/form")
//	    public String showAppointmentForm(Model model) {
//	        // Add any necessary model attributes
//	        return "doctor_appointment";
//	    }
//
//	    // Process the form submission
//	    @PostMapping("/appointments")
//	    public String bookAppointment(@ModelAttribute Appointment1 appointment, Model model) {
//	        // Perform validation and insert appointment into the database
//	    	appointmentService1.bookAppointment(appointment);
//	        // Add success message to the model
//	        model.addAttribute("successMessage", "Appointment booked successfully");
//	        // Redirect to a success page or return a success view
//	        return "redirect:/appointments";
//	    }
//	
	
	@Autowired
    private UserRepository userRepo;

	@RequestMapping("/")
	public String displayMainPage() {
		return "mainPage";
	}
	
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "SignUpPage";
    }
   
    @PostMapping("/appointments")
    public String createAppointment(@ModelAttribute("appointment") Doctor appointment, Model model) {
        // Logic for creating a new appointment

        // Call the service layer to persist the appointment object to the database
    	appointmentservice.createAppointment(appointment);

        // Redirect to a success page or display a success message
        model.addAttribute("successMessage", "Appointment booked successfully!");
        return "successPage";
   
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_sucess";
    }
	
	@RequestMapping("/home")
	public String displayHomePage() {
		return "HomePage";
	}
	
	@RequestMapping("/doctorlist")
	public String viewAllDoctors(Principal principal,Model model) {
		List<Doctor> listDoctors = service.listAll();
		String[] admin = new String[] {"arjunv2001@gmail.com", "vkironv@yahoo.com"};
		String currUser = principal.getName();
		model.addAttribute(currUser);
		List<String> adminList = new ArrayList<>(Arrays.asList(admin));
		boolean cond = adminList.contains(currUser);
		model.addAttribute("cond",cond);
		model.addAttribute("listDoctors", listDoctors);
		return "doctorlist";
	}
	
	@RequestMapping("/search")
	public String viewDoctorsListPage(Doctor doctor, Model model, String keyword) {
		  if(keyword!=null) {
			   List<Doctor> listDoctors = service.getByKeyword(keyword);
			   model.addAttribute("listDoctors", listDoctors);
			  }
			  else {
			  List<Doctor> listDoctors = service.listAll();
			  model.addAttribute("listDoctors", listDoctors);
			  }
			  return "doctorlist";
	}
	
	@RequestMapping("/new")
	public String showNewHospitalPage(Model model) {
		Doctor Hospital = new Doctor();
	    model.addAttribute("Hospital",Hospital);
	    return "new_doctor";
	}
	
	@RequestMapping("/profile")
	public String showProfile(Principal principal,Model model,String keyword) {
		    List<appointment> listdoctorappoinment = appointmentservice.getByKeyword(principal.getName());
		    model.addAttribute("listdoctorappoinment", listdoctorappoinment);
	        return "profile";
	}
	
	@RequestMapping("/book")
	public String showdoctorappoinmentbookpage(Principal principal,Model model) {
		appointment doctorappoinment = new appointment();
	    String currUser = principal.getName();
	    doctorappoinment.setUser(currUser);
	    model.addAttribute("doctorappoinment",doctorappoinment);
	    List<Doctor> doctorappoinment1 = service.listAll();
	    model.addAttribute("doctorappoinment", doctorappoinment1);
		//model.addAttribute("currUser",currUser);
	    return "doctor_appoinment";
	}
	
	@RequestMapping(value = "/saveappointment", method = RequestMethod.POST)
	public String savedoctorappoinment(@ModelAttribute("doctorappoinment") appointment doctorappoinment) {
		List<Doctor> doctor = service.findByDoctorName(doctorappoinment.getDoctor());
		Doctor rec = doctor.get(0);
		//long remTickets = rec.getSeats() - appointment.getHospital();
		//rec.setSeats(remTickets);
		//service.save(rec);
		appointmentservice.save(doctorappoinment);
	    return "redirect:/confirm";
	}
	
	@RequestMapping("/confirm")
	public String confirmation() {
		return "confirmation";
	}

//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public String saveDoctor(@ModelAttribute("Doctor") Doctor doctor) {
//		doctor.setSeats(300);
//	    service.save(doctor);
//	    return "redirect:/doctorappoinment";
//	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditMoviePage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_doctor");
	    Doctor hospital = service.get(id);
	    mav.addObject("Hospital", hospital);
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteMovie(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
	@RequestMapping("/deleteticket/{id}")
	public String deleteMovieTicket(@PathVariable(name = "id") int id) {
		appointment doctorappoinment = appointmentservice.get(id);
		List<Doctor> movie = service.findByDoctorName(doctorappoinment.getDoctor());
		Doctor rec = movie.get(0);
		//long remTickets = rec.getSeats() + movieticket.getHospital();
		//rec.setSeats(remTickets);
		service.save(rec);
		appointmentservice.delete(id);
	    return "redirect:/profile";       
	}
}
