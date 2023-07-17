package com.searchmodule.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.test.Basetest;
import com.searchmodule.pages.SearchPage;

public class SearchTest extends Basetest{
	@Test
	@Parameters({"keyword"})
	public void search(String keyword) {
		SearchPage searchPage = new SearchPage(driver);
		searchPage.goTo();
		searchPage.doSearch(keyword);
		searchPage.goToVideos();
		int size = searchPage.getResult();
		assertTrue(size>0);
	}

}
