package models;

import java.util.concurrent.CompletableFuture;

/**
 * Create - Read - Update - Delete interface
 * @author Admin
 *
 */
public interface ICRUD {
	void read();
	void create();
	void update();
	void delete();
}
