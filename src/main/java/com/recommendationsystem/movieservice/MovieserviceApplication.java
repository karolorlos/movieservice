package com.recommendationsystem.movieservice;

import com.recommendationsystem.movieservice.Model.Movie;
import com.recommendationsystem.movieservice.Model.Rating;
import com.recommendationsystem.movieservice.Model.User;
import com.recommendationsystem.movieservice.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@ComponentScan({"com.recommendationsystem.movieservice"})
@EntityScan("com.recommendationsystem.movieservice.Model")

public class MovieserviceApplication {



	public static void main(String[] args) {

		SpringApplication.run(MovieserviceApplication.class, args);

	}

	/**
	 * Save users and students to H2 DB for testing
	 * @param //repository
	 * @return
	 */
	@Bean
	public CommandLineRunner demo(UserRepository urepository, MovieRepository movieRepository, RatingRepository ratingRepository) {
		return (args) -> {
			//movies
			Movie movie1 = new Movie("Zielona Mila","dramat",1999,"Zielona mila jest historią opowiedzianą przez wiekowego Paula Edgecomba (Dabbs Greer) w domu spokojnej starości. Po obejrzeniu filmu pt.: „Top Hat” przypomina sobie pewne zdarzenia i opowiada swojej przyjaciółce o niezwykłym więźniu którego spotkał w lecie 1935 roku, gdy pracował jako strażnik więźniów skazanych na karę śmierci w więzieniu w Luizjanie. Blok, na którym pracował, nazywany był zieloną milą ze względu na kolor linoleum leżącego na podłodze. Niemym bohaterem celi śmierci jest również krzesło elektryczne (zwane Starą Iskrówą, Old Sparky), oczekujące na swe ofiary. Pewnego dnia na oddział trafia olbrzymi, czarnoskóry John Coffey (Michael Clarke Duncan), oskarżony o zamordowanie dwóch białych dziewczynek. Coffey okazuje się łagodnym olbrzymem, bojącym się ciemności i czasem płaczącym. Niedługo ujawnia swe niezwykłe zdolności uzdrawiające przez wyleczenie Paula Edgecomba z zapalenia pęcherza moczowego oraz ożywienie myszy. Percy Wetmore (Doug Hutchison), jest wrednym sadystycznym strażnikiem lubiącym sprawiać cierpienie innym. Ma również wysoko postawionego wujka (ściślej samego gubernatora stanu), który „załatwił” mu tę pracę i nie bardzo można pozbyć się go z obecnego stanowiska. Za pozwolenie wykonania wyroku obiecuje przenieść się na inne miejsce. Percy umyślnie nie moczy gąbki, umożliwiającej lepszy przepływ prądu ale nie spodziewał się takiego efektu. Więzień umierał przez długi czas w strasznych męczarniach, zapala się i wywołuje panikę wśród świadków. Strażnicy wykorzystują zdolności uzdrawiające Johna, by wyleczyć żonę naczelnika więzienia. Po powrocie do więzienia John przenosi jej chorobę na Percy’ego Wetmore’a. Pod wpływem Coffeya kompletnie oderwany od rzeczywistości Percy zabija jednego z więźniów – Dzikiego Billa, a następnie zostaje umieszczony w zakładzie dla obłąkanych. Paul utwierdza się w przekonaniu, że John jest niewinny, a proces był czysto formalny (znaleziono go tulącego do siebie martwe dziewczynki – czarny musi być winny, choć w rzeczywistości wcale ich nie zabił, tylko znalazł i próbował pomóc za pomocą swoich zdolności, prawdziwym mordercą był Dziki Bill), ale nie ma na to dowodów. Egzekucja musi się odbyć. Przed śmiercią John ogląda ten sam film co Paul na początku filmu.","https://www.granice.pl/sys6/pliki/okladka_k/f465e64ae804b0774f2ca60cac27e7c2.jpeg");
			movieRepository.save(new Movie("Skazani na Shawshank","dramat",1994,"Film nakręcony na podstawie książki mistrza horrorów Stephena Kinga. Andy Dufresne (Tim Robbins), dobrze zarabiający bankier z Nowej Anglii, zostaje oskarżony o podwójne zabójstwo - swojej żony i jej kochanka. Uparcie twierdzi, że jest niewinny, ale dzięki niezbitym dowodom zostaje skazany na podwójne dożywocie w więzieniu Shawshank. Shawshank rządzi hipokryta i fanatyk biblijny, naczelnik Norton (Bob Gunton), a wraz z nim sadystyczni strażnicy. Andy już po kilku dniach poznaje brutalną, więzienną rzeczywistość, ale dzięki wrodzonej inteligencji, sprytowi oraz pomocy przyjaciela Ellisa Boyda Reda  Reddinga (Morgan Freeman) udaje mu się zachować nadzieję, która pozwoli dokonać zemsty.","http://stephenking.pl/wp-content/uploads/2015/07/Skazani-na-Shawshank-1994-DVD.jpg"));
			movieRepository.save(new Movie("Forest Gump","komedia",1994,"Forrest Gump to romantyczna historia, w której Tom Hanks wcielił się w tytułową postać - nierozgarniętego młodego człowieka o wielkim sercu i zdolności do odnajdywania się w największych wydarzeniach w historii USA, począwszy od swego dzieciństwa w latach 50-tych. Po tym, jak staje się gwiazdą footballu, odznaczonym bohaterem wojennym i odnoszącym sukcesy biznesmenem, główny bohater zyskuje status osobistości, lecz nigdy nie rezygnuje z poszukiwania tego, co dla niego najważniejsze - miłości swej przyjaciółki, Jenny Curran. Forrest jest małym chłopcem, kiedy jego ojciec porzuca rodzinę, a matka utrzymuje siebie i syna biorąc pod swój dach lokatorów. Kiedy okazuje się, że jej chłopiec ma bardzo niski iloraz inteligencji, pozostaje nieustraszona w swoim przekonaniu, że ma on takie same możliwości, jak każdy inny. To prawda - takie same, a nawet dużo większe. W całym swym życiu Forrest niezamierzenie znajduje się twarzą w twarz z wieloma legendarnymi postaciami lat 50-tych, 60-tych i 70-tych. Wiedzie go to na boisko piłki nożnej, poprzez dżungle Wietnamu, Waszyngton, Chiny, Nowy Jork, do Luizjany i w wiele innych miejsc, a wszystko to relacjonuje on w swych poruszających i wstrząsających opowieściach przypadkowo spotkanym osobom.","https://ia.media-imdb.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UY268_CR1,0,182,268_AL_.jpg"));
			movieRepository.save(new Movie("Leon Zawodowiec","gangsterski",1994,"Film o relacji pomiędzy dwunastoletnią dziewczynką a zawodowym mordercą. Ojciec Matyldy zajmuje się przechowywaniem  brudnego towaru, który przywłaszcza sobie w niewielkich porcjach. Zostaje to zauważone. Matylda szczęśliwie wyszła właśnie do sklepu, gdy skorumpowani policjanci-zabójcy mordują całą rodzinę, łącznie z ukochanym młodszym braciszkiem Matyldy. Dziewczynka mimo szoku myśli trzeźwo – nie kieruje się do domu, ale idzie wprost do drzwi sąsiada – Leona, płatnego zabójcy. Ta decyzja ratuje jej życie. Leon niechętnie postanawia zaopiekować się Matyldą, co wkrótce owocuje przyjaźnią pomiędzy nimi. Dziewczynka uczy się zawodu sprzątacza – jak określa swój fach Leon i pragnie pomścić śmierć braciszka.","http://cf2-taniaksiazka.statiki.pl/images/large/4D0/GPLAKATU_G297101.jpg"));
			movieRepository.save(new Movie("Matrix","Sci-Fi",1999,"Neo (Keanu Reeves) jest genialnym hakerem. Pewnego dnia nawiązuje z nim kontakt tajemniczy Morfeusz (Laurence Fishburne) - człowiek, który obiecuje przekazać mu wiedzę o rzeczywistości, w jakiej żyje. Prawdę o dwóch światach: prawdziwym i wygenerowanym, który ma tylko udawać rzeczywistość. Neo przystaje do grupy Morfeusza i zaczyna dostrzegać, że świat, w którym egzystował to fikcja, a jego życiem cały czas ktoś kierował. Kolejne stopnie wtajemniczenia stawiają przed Neo nowe pytania. Czym jest Matrix i komu służy? I jaką rolę w planie Morfeusza ma do spełnienia on sam?","https://upload.wikimedia.org/wikipedia/ca/7/7a/Matrix.jpg"));
			movieRepository.save(new Movie("Milczenie owiec","thriller",1991,"Agentce FBI, Clarise Starling, zostaje powierzona sprawa \"Buffalo Billa\", seryjnego mordercy wyróżniającego się szczególnym okrucieństwem wobec swych ofiar, które odziera ze skóry. Sprawę pomaga jej rozwikłać inny, niezwykle inteligentny i niebezpieczny morderca - Hannibal Lecter - pacjent więziennego centrum psychiatrycznego. Hannibal jest zauroczony Clarice, a w zamian za odkrywanie przez nią części swej przeszłości, kawałek po kawałku odkrywa dla niej tajemnicę morderstw.","http://1.fwcdn.pl/po/10/47/1047/7714177.6.jpg "));
			movieRepository.save(new Movie("Avatar","Sci-Fi",2009,"Długo oczekiwany film Jamesa Camerona zrealizowany z wielkim rozmachem porównywalnym do \"Titanica\". Szacowany budżet na poziomie 237 milionów dolarów daje mu trzecie miejsce pod tym względem w historii kina. \"Avatar\" został wykonany w dwóch technikach: tradycyjnej 2D i zupełnie nowej technice 3D. Zdjęcia do filmu zostały wykonane w 2007 roku, natomiast na postprodukcję przeznaczono dwa lata. \"Avatar\" opowiada historię sparaliżowanego byłego komandosa, który dostajee szansę odzyskania zdrowego ciała. Musi jednak wziąć udział w specjalnym programie militarnym o nazwie Avatar. W rolach głównych między innymi Sam Worthington, Sigourney Weaver, Michelle Rodriguez.","https://i.pinimg.com/736x/29/a8/20/29a82067b71bd9e3df95e1c0ba5c4daf--fantasy-art-avatar-jake-sully.jpg"));
			movieRepository.save(new Movie("Shrek","familijny",2001,"W bagnie żył olbrzym Shrek, którego cenna samotność została nagle zakłócona inwazją dokuczliwych postaci z bajek. Ślepe myszki buszują w zapasach olbrzyma, zły wilk sypia w jego łóżku, a trzy świnki buszują po jego samotni. Wszystkie te postaci zostały wypędzone ze swego królestwa przez złego Lorda Farquaada. Zdecydowany ocalić ich dom - nie mówiąc już o swoim - Shrek porozumiewa się z Farquaadem i wyrusza na ratunek pięknej księżniczce Fionie, która ma zostać żoną Lorda. W misji towarzyszy mu przemądrzały Osioł, który zrobi dla Shreka wszystko z wyjątkiem... przestania mielenia ozorem. Ocalenie księżniczki przed ziejącym ogniem smokiem okazuje się być najmniejszym problemem przyjaciół, kiedy to zostaje odkryty głęboko skrywany, mroczny sekret Fiony.", "http://ecsmedia.pl/c/shrek-w-iext40685816.jpg"));
			movieRepository.save(new Movie("Titanic","dramat",1997,"W słoneczny, kwietniowy dzień w 1912 roku, na angielskim wybrzeżu arystokratyczna rodzina wraz z 17-letnią Rose (Kate Winslet) wchodzi na pokład Titanica, udając się w podróż do Stanów Zjednoczonych. Pół godziny później grający w pokera w tanim, portowym barze 23-letni Jack Dawson (Leonardo DiCaprio) wygrywa szczęśliwym trafem bilet III klasy na rejs Titanikiem. W ostatniej chwili przed wypłynięciem statku wbiega na jego pokład. Tak zaczyna się wielka historia miłosna, która ma odmienić życie pięknej, bogatej, lecz nieszczęśliwej Rose oraz biednego, nie mającego nic prócz talentu malarskiego, Jacka.","https://vignette.wikia.nocookie.net/movies/images/9/92/Titanic.jpg/revision/latest?cb=20160212171325&path-prefix=pl"));
			movieRepository.save(new Movie("Pulp Fiction","gangsterski",1994,"Płatni mordercy, Jules (Samuel L. Jackson) i Vincent (John Travolta), dostają zlecenie, by odzyskać z rąk przypadkowych rabusiów tajemniczą walizkę bossa mafii. Nie dość tego, Vincent dostaje kolejną robotę - na czas nieobecności gangstera w mieście ma zaopiekować się jego poszukującą wrażeń żoną Mią (Uma Thurman). Vincent i Jules niespodziewanie wpadają po uszy, gdy przypadkowo zabijają zakładnika. Kłopoty ma też podupadły bokser (Bruce Willis), który otrzymał dużą sumę za przegranie swojej walki. Walkę jednak wygrywa, a Los Angeles staje się od tej chwili dla niego za małe. Specjaliści od mokrej roboty będą mieli co robić...","https://ia.media-imdb.com/images/M/MV5BMTkxMTA5OTAzMl5BMl5BanBnXkFtZTgwNjA5MDc3NjE@._V1_UX182_CR0,0,182,268_AL_.jpg"));







			ratingRepository.save(new Rating(0.6d, 1L,2L));
			ratingRepository.save(new Rating(0.2d, 2L,2L));
			ratingRepository.save(new Rating(0.3d, 3L,2L));
			ratingRepository.save(new Rating(0.6d, 4L,2L));
			ratingRepository.save(new Rating(0.1d, 1L,1L));
			ratingRepository.save(new Rating(0.3d, 2L,1L));
			ratingRepository.save(new Rating(0.3d, 3L,1L));
			ratingRepository.save(new Rating(0.3d, 4L,1L));
			ratingRepository.save(new Rating(0.3d, 5L,1L));
			ratingRepository.save(new Rating(0.2d, 6L,1L));
			ratingRepository.save(new Rating(0.3d, 7L,1L));
			ratingRepository.save(new Rating(0.7d, 8L,1L));
			ratingRepository.save(new Rating(0.8d, 9L,1L));
			ratingRepository.save(new Rating(0.3d, 1L,3L));
			ratingRepository.save(new Rating(0.4d, 2L,3L));
			ratingRepository.save(new Rating(0.2d, 3L,3L));
			ratingRepository.save(new Rating(0.5d, 4L,3L));
			ratingRepository.save(new Rating(0.6d, 5L,3L));
			ratingRepository.save(new Rating(0.1d, 6L,3L));
			ratingRepository.save(new Rating(0.1d, 7L,3L));
			ratingRepository.save(new Rating(0.6d, 8L,3L));
			ratingRepository.save(new Rating(0.1d, 9L,3L));
			ratingRepository.save(new Rating(0.1d, 1L,4L));
			ratingRepository.save(new Rating(0.1d, 2L,4L));
			ratingRepository.save(new Rating(0.2d, 3L,4L));
			ratingRepository.save(new Rating(0.1d, 4L,4L));
			ratingRepository.save(new Rating(0.5d, 5L,4L));
			ratingRepository.save(new Rating(0.5d, 6L,4L));
			ratingRepository.save(new Rating(0.1d, 7L,4L));
			ratingRepository.save(new Rating(0.2d, 8L,4L));
			ratingRepository.save(new Rating(0.1d, 9L,4L));
			ratingRepository.save(new Rating(0.1d, 1L,5L));
			ratingRepository.save(new Rating(0.2d, 2L,5L));
			ratingRepository.save(new Rating(0.1d, 3L,5L));
			ratingRepository.save(new Rating(0.2d, 4L,5L));
			ratingRepository.save(new Rating(0.1d, 5L,5L));
			ratingRepository.save(new Rating(0.2d, 6L,5L));
			ratingRepository.save(new Rating(0.1d, 7L,5L));
			ratingRepository.save(new Rating(0.1d, 8L,5L));
			ratingRepository.save(new Rating(0.5d, 9L,5L));
			ratingRepository.save(new Rating(0.6d, 1L,6L));
			ratingRepository.save(new Rating(0.7d, 2L,6L));
			ratingRepository.save(new Rating(0.6d, 3L,6L));
			ratingRepository.save(new Rating(0.1d, 4L,6L));
			ratingRepository.save(new Rating(0.8d, 5L,6L));
			ratingRepository.save(new Rating(0.7d, 6L,6L));
			ratingRepository.save(new Rating(0.8d, 7L,6L));
			ratingRepository.save(new Rating(0.6d, 8L,6L));
			ratingRepository.save(new Rating(0.3d, 9L,6L));


			// Create users with BCrypt encoded password (user/user, admin/admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			User user3 = new User("user3", "$2a$10$vQnEOTfMyAp7bKWSYQ88t.5AiV0olHFrYDCx28KBXzlWdaDrqi7RC", "USER");
			User user4 = new User("user4", "$2a$10$wdoSWZXf/sf9uK/fkz1uae.MQiC1eK99ONloo5COUL087sFEz4hPu", "USER");
			User user5 = new User("user5", "$2a$10$YAMDaxoFyy4npODuebB7oeG2uByfevtMOjgZ23RPgvGzk4m./62Ty", "USER");
			User user6 = new User("user6", "$2a$10$FViCHzT3yEH5dp2xkEFVKO.yuz8zEu9da9l90JMXUMn7PqIK2TOYq", "USER");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			urepository.save(user4);
			urepository.save(user5);
			urepository.save(user6);

		};
	}


}
