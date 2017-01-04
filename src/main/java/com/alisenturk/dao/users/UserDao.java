package com.alisenturk.dao.users;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alisenturk.model.User;

@Transactional
public interface UserDao extends JpaRepository<User, Long> {
	
	/*
	@Query("select h.city as city, h.name as name, avg(r.rating) as averageRating "
			+ "from Hotel h left outer join h.reviews r where h.city = ?1 group by h")
	Page<HotelSummary> findByCity(City city, Pageable pageable);

	@Query("select r.rating as rating, count(r) as count "
			+ "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
	List<RatingCount> findRatingCounts(Hotel hotel);
	**/
	/**
	 @Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
			    countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
			    nativeQuery = true)
			  Page<User> findByLastname(String lastname, Pageable pageable);
	 
	 @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
	  User findByEmailAddress(String emailAddress);
	 
	 @Query("select u from User u where u.firstname like %?1")
	  List<User> findByFirstnameEndsWith(String firstname);

	 * @Query("select u from User u where u.lastname like ?1%")
  		List<User> findByAndSort(String lastname, Sort sort);
  		
  		@Query("select u.id, LENGTH(u.firstname) as fn_len from User u where u.lastname like ?1%")
  		List<Object[]> findByAsArrayAndSort(String lastname, Sort sort);
		repo.findByAndSort("lannister", new Sort("firstname"));               
		repo.findByAndSort("stark", new Sort("LENGTH(firstname)"));           
		repo.findByAndSort("targaryen", JpaSort.unsafe("LENGTH(firstname)")); 
		repo.findByAsArrayAndSort("bolton", new Sort("fn_len"));              
	 * 
	 * **/
	 
}