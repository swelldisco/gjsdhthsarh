package com.example.book_club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.book_club.books.Book;
import com.example.book_club.books.BookRepository;
import com.example.book_club.users.User;
import com.example.book_club.users.UserRepository;

@SpringBootApplication
public class BookClubApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookClubApplication.class, args);
	}

	// loading test books into repository
	@Override
	public void run(String... args) throws Exception {
		Book[] testBooks = new Book[] {
            new Book("Billy Bob", "Amazing Mines of the American West", 1987),
            new Book("Jim Bob", "Country Cooking with Jim Bob", 2014),
            new Book("Jebadiah \"No Teeth\" Jones", "Catfish Noodling in the Ozarks", 1998),
            new Book("Jethro McGimpy", "Modern Information Security Solutions for Back Country Rednecks", 2018),
            new Book("Jethro McGimpy", "Redneck Solutions for Cloud Migration", 2016),
            new Book("Jethro McGimpy", "The Datacenters Have Eyes: Navigating the International Legal Requirements for Local User Information Storage", 2022),
            new Book("Jethro McGimpy", "A Hillbilly Sysadmin\'s Guide to Meeting City Slicker Sarbanes-Oxley Compliance Needs", 2015),
            new Book("Jethro McGimpy", "There\'s No Minimum Number of Teeth to be a Site Reliability Engineer", 2021),
            new Book("Billy Bob", "Black Damp Dangers and Other Lessons Learned from Twenty Years of Managing Coal Mines in the Appalacians.", 1992),
            new Book("Crabby McClams", "Back Country Bedtime Stories", 2003),
            new Book("Cletus Monroe", "Financials Leave No Footprints: A Practical Guide to Forensic Accounting", 2016),
            new Book("Bubba Beauregard Jackson", "Applied Genetics for Maintaining and Improving Livestock Health", 1993),
            new Book("Elmer Dale", "An Introduction to Music Theory for the Banjo Picker", 2008),
            new Book("Bubba Beauregard Jackson", "A Tale of Two Toes Too Many", 1973),
			new Book("Ezekiel Dimwit Holt", "Jethro McGimpy, a Hillbilly Legend in Information Security", 2023),
            new Book("An Author", "A Title", 1987)
		};

		for (Book book : testBooks) {
			bookRepository.save(book);
		}

        User[] testUsers = new User[] {
            new User("Waffles", "waffles@thisemail.com"),
            new User("gingersnap", "tammy.flanders@aol.com"),
            new User("legolas4evah", "e_mccoy@email.net"),
            new User("xXSepirothXx", "remuswright@hail.org"),
            new User("i_aim_to_please_with_a_slice_of_cheese", "cheesenips@stormmail.com"),
            new User("jebadiah", "jebby@UofT.edu")
        };

        // User testUser = new User("myName", "Myemail@mail.com");
        // userRepository.save(testUser);
        for (User user : testUsers) {
            userRepository.save(user);
        }
	}

}
