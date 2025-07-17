package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseBAgedBrieTest {
	private static final String AGED_BRIE = "Aged Brie";
	private static final int NOT_EXPIRED_SELLIN = 4;
	private static final int EXPIRED_SELLIN = -1;
	private static final int DEFAULT_QUALITY = 3;
	private static final int MAXIMUM_QUALITY = 50;

	@Test
	public void unexpiredAgedBrie_qualityIncreasesBy1() {
		GildedRose app = createGildeRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		app.updateQuality();

		Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredAgedBrie_qualityIncreasesBy2() {
		GildedRose app = createGildeRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredAgedBrie_qualityDoesNotGoBeyondMaximum() {
		GildedRose app = createGildeRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, MAXIMUM_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, MAXIMUM_QUALITY);
		assertItem(expected, app.items[0]);
	}

	private GildedRose createGildeRoseWithOneItem(String defaultItem, int sellIn, int quality) {
		Item item = new Item(defaultItem, sellIn, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}

    private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}
}
