package com.helpinghandslocation.helpinghandslocation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.helpinghandslocation.helpinghandslocation.controllers.LocationController;
import com.helpinghandslocation.helpinghandslocation.dto.LocationTagDTO;
import com.helpinghandslocation.helpinghandslocation.dto.response.LocationCreatorDTO;
import com.helpinghandslocation.helpinghandslocation.models.Location;
import com.helpinghandslocation.helpinghandslocation.models.Tag;
import com.helpinghandslocation.helpinghandslocation.repositories.LocationRepository;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
class HelpinghandslocationApplicationTests {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationController locationController;

	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
	@Order(2) 
	void addLocationViaController(){

		LocationTagDTO testlocationTagDTO = new LocationTagDTO("Test Name1", 1.1,1.1, "Test Address1", List.of(2L,5L));
		assertNotNull(locationController.saveLocations(testlocationTagDTO).getBody(), "When saving the object, it needs to be returned");

		testlocationTagDTO =  new LocationTagDTO("Test Name2", 2.2,2.2, "Test Address2", List.of(2L,7L));
		assertNotNull(locationController.saveLocations(testlocationTagDTO).getBody(), "When saving the object, it needs to be returned");

	}

	@Test 
	@Order(3)
	void allLocationsReturnedWithoutTagIds(){
		long allRepositoryLocationsCount = locationRepository.count();

		assertTrue(allRepositoryLocationsCount>0, "Repository should have data!");

		List<Location> allControllerLocations = (List<Location>) locationController.getLocations(null).getBody();

		assertNotNull(allControllerLocations, "Controller should return a list");
		assertEquals(allRepositoryLocationsCount, allControllerLocations.size(), "Controller should return all locations");
	}

	@Test 
	@Order(4)
	void allLocationsHaveRequestedTags(){
		
		List <Long> requestedTagIds = List.of(2L, 5L);
		List<LocationCreatorDTO> allControllerLocations = (List<LocationCreatorDTO>) locationController.getLocations(requestedTagIds).getBody();		

		assertNotNull(allControllerLocations, "Controller should return a list");

		allControllerLocations.forEach(location->{
			List<Tag> tags = location.getTags();
			List<Long> locationTagIds = tags.stream().map(Tag::getId).collect(Collectors.toList());
			assertTrue(locationTagIds.containsAll(requestedTagIds), "Location tags should include all requested tags");				
		});
		
	}

}
