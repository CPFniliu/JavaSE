package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import entity.Place;

public class SortedPlaces {
	
	private SortedSet<ScorePlace> commonScorePlace;
	
	public boolean hasData() {
		return commonScorePlace != null;
	}
	
	public void addOrInit(Place place, int score){
		if (score > 0) {
			// FIXME 过滤掉小于0的
			if (commonScorePlace == null) {
				commonScorePlace = new TreeSet<>();
			}
			commonScorePlace.add(new ScorePlace(place, score));
		}
	}
	
	public List<Place> getSortedPlaces(){
		List<Place> places = new ArrayList<>(commonScorePlace.size());
		for (ScorePlace item : commonScorePlace) {
			places.add(item.place);
		}
		return places;
	}
	
	

	@Override
	public String toString() {
		return "SortedPlaces [commonScorePlace=" + commonScorePlace + "]";
	}



	private class ScorePlace implements Comparable<ScorePlace> {
		public final Place place;
		public final int score;
		
		public ScorePlace(Place place, int score) {
			this.place = place;
			this.score = score;
		}
		
		@Override
		public int compareTo(ScorePlace o) {
			int r = o.score - score;
			if (r == 0) {
				r = o.place.x - place.x;
			}
			if (r == 0) {
				r = o.place.y - place.y;
			}
			return r;
		}

		@Override
		public String toString() {
			return "ScorePlace [place=" + place + ", score=" + score + "]";
		}
		
	}
	

}
