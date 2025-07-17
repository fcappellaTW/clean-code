package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseCBackstagePassesTest {
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final int SELLIN_GREATER_THAN_10 = 15;
	private static final int SELLIN_BETWEEN_5_AND_10 = 7;
	private static final int SELLIN_LESS_THAN_5 = 4;
	private static final int DEFAULT_QUALITY = 3;

	@Test
	public void backStagePassesBeyond10Days_qualityIncreasesBy1() {
		GildedRose app = createGildeRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10 - 1, DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backStagePassesBetween5And10Days_qualityIncreasesBy2() {
		GildedRose app = createGildeRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_BETWEEN_5_AND_10, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES, SELLIN_BETWEEN_5_AND_10 - 1, DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void backStagePassesLessThan5Days_qualityIncreasesBy3() {
		GildedRose app = createGildeRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_LESS_THAN_5, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES, SELLIN_LESS_THAN_5 - 1, DEFAULT_QUALITY + 3);
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