package ie.tudublin;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{
	ArrayList<Nematode> nematodes;

	public void keyPressed()
	{
		switch (key) {
			case LEFT: {

				break;
			}
			case RIGHT: {

				break;
			}
			default: {
				break;
			}
		}
	}


	public void settings()
	{
		size(800, 800);
	}

	public void setup()
	{
		colorMode(HSB);
		background(0);
		smooth();

		// Nematodes
		nematodes = new ArrayList<Nematode>();
		loadNematodes();
		print("Nematodes loaded");
	}


	public void loadNematodes()
	{
		Table table = loadTable("nematodes.csv", "header");
		for (TableRow tr : table.rows()) {
			nematodes.add(new Nematode(tr));
		}
	}


	public void draw()
	{
	}
}
