package ie.tudublin;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class NematodeVisualiser extends PApplet
{
	private ArrayList<Nematode> nematodes;
	private int index;

	public void keyPressed()
	{
		switch (keyCode) {
			case LEFT: {
				index = (--index + nematodes.size()) % nematodes.size();
				break;
			}
			case RIGHT: {
				index = (++index + nematodes.size()) % nematodes.size();
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
		int id = 0;
		for (TableRow tr : table.rows()) {
			nematodes.add(new Nematode(id++, this, tr));
		}
	}


	public void draw()
	{
		background(0);
		text(index, 10, 10);
		handleNematodes();
	}

	private void handleNematodes() {
		Iterator<Nematode> itrNematode = nematodes.iterator();
		while (itrNematode.hasNext()) {
			Nematode nematode = itrNematode.next();
			nematode.render();
			nematode.update(index, nematodes.size());
		}
	}
}
