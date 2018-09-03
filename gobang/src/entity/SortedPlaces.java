package entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedPlaces {
	
	private SortedSet<ScorePlace> commonScorePlace;
	
	public boolean hasData() {
		return commonScorePlace != null;
	}
	
	public void addOrInit(Place place, int score){
		if (commonScorePlace == null) {
			commonScorePlace = new TreeSet<>(new Comparator<ScorePlace>(){

				@Override
				public int compare(ScorePlace o1, ScorePlace o2) {
					int r = o2.score - o1.score;
//					if (r == 0) {
//						r = o2.place.x - o1.place.x;
//					}
//					if (r == 0) {
//						r = o2.place.y - o1.place.y;
//					}
					return r;
				}
				
			});
		}
		commonScorePlace.add(new ScorePlace(place, score));
	}
	
	public List<Place> getSortedPlaces(){
		List<Place> places = new ArrayList<>(commonScorePlace.size());
		for (ScorePlace item : commonScorePlace) {
			places.add(item.place);
		}
		return places;
	}
	

	private class ScorePlace {
		public Place place;
		public int score;
		
		public ScorePlace(Place place, int score) {
			this.place = place;
			this.score = score;
		}
	}
}
